package com.start.web.service;

import com.start.web.domain.Role;
import com.start.web.domain.User;
import com.start.web.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private CommentService commentService;

    @Autowired
    private MessageService messageService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return userRepo.findByUsername(username);
    }

    public boolean addUser(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            return false;
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.BLOCKED));
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()) );

        userRepo.save(user);

        sendMessage(user);

        return true;
    }

    private void sendMessage(User user) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Hello, %s! \n" +
                            "Welcome to Hello Web. Please, visit next link: http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );

            mailSender.send(user.getEmail(), "Activation code", message);
        }
    }

    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);

        if (user == null) {
            return false;
        }

        user.setActivationCode(null);
        user.getRoles().clear();
        user.getRoles().add(Role.USER); //Добавление роли USER после подтверждения мыла, до подтверждения роль BLOCKED.

        userRepo.save(user);

        return true;
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public void deleteUser(User user) {
        commentService.deleteUserComment(user);

        commentService.removeUserLikes(user);

        messageService.deleteMessageByAuthor(user);

        userRepo.delete(user);
    }

    public void setUserAdmin(User user) {
        user.getRoles().clear();
        user.getRoles().add(Role.valueOf("USER"));
        user.getRoles().add(Role.valueOf("ADMIN"));

        userRepo.save(user);
    }

    public void setUserBlock(User user) {
        user.getRoles().clear();
        user.getRoles().add(Role.BLOCKED);

        userRepo.save(user);
    }

    public void setUserUnblock(User user) {
        if(user.isRole("ADMIN")) {
            userRepo.save(user);
        }

        user.getRoles().clear();
        user.getRoles().add(Role.valueOf("USER"));

        userRepo.save(user);
    }
}