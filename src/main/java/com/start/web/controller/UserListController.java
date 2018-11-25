package com.start.web.controller;

import com.start.web.domain.Comment;
import com.start.web.domain.User;
import com.start.web.domain.util.UserHelper;
import com.start.web.repos.CommentRepo;
import com.start.web.repos.MessageRepo;
import com.start.web.repos.UserRepo;
import com.start.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class UserListController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentRepo commentRepo;

    @GetMapping("/user")
    public String userList(@AuthenticationPrincipal User user,
                           Model model) {
        if(user.isRole("ADMIN")) {
            model.addAttribute("users", userService.findAll());

            return "userList";
        }
        else {
            return "redirect:/";
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/updateuserlist")
    public String changeUserList(@RequestParam Map<String, String> form,
                                 @AuthenticationPrincipal User authUser) {
        List<User> users = userService.findAll();
        boolean exit = false;

        if(form.keySet().contains("set-admin")) {
            for(String key : form.keySet()){
                if(users.contains(userRepo.findByUsername(key))) {
                    User user = userRepo.findByUsername(key);

                    userRepo.save(UserHelper.setUserAdmin(user));
                }
            }
        }
        else if(form.keySet().contains("delete-users")) {
            for(String key : form.keySet()){
                if(users.contains(userRepo.findByUsername(key))) {
                    User user = userRepo.findByUsername(key);

                    if(authUser.getId().equals(user.getId())) {
                        exit = true;
                    }

                    commentRepo.deleteAll(commentRepo.findByAuthor(user));

                    Iterable<Comment> comments = commentRepo.findAll();

                    for(Comment comment : comments) {
                        comment.getLikes().remove(user);

                    }

                    messageRepo.deleteAll(messageRepo.findByAuthor(user, null));

                    userRepo.delete(user);
                }
            }
        }
        else if(form.keySet().contains("block-users")) {
            for(String key : form.keySet()){

                if(users.contains(userRepo.findByUsername(key))) {
                    User user = userRepo.findByUsername(key);

                    if(authUser.getId().equals(user.getId())) {
                        exit = true;
                    }

                    userRepo.save(UserHelper.setUserBlock(user));
                }

            }
        }
        else if(form.keySet().contains("unblock-users")) {
            for(String key : form.keySet()){
                if(users.contains(userRepo.findByUsername(key))) {
                    User user = userRepo.findByUsername(key);

                    userRepo.save(UserHelper.setUserUnblock(user));
                }
            }
        }

        if(exit) {
            SecurityContextHolder.clearContext();
        }

        return "redirect:/user";
    }
} 