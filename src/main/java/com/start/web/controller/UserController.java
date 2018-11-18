package com.start.web.controller;

import com.start.web.domain.Role;
import com.start.web.domain.User;
import com.start.web.repos.UserRepo;
import com.start.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.findAll());

        return "userList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());

       return "userEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ) {
        userService.saveUser(user, username, form);

        return "redirect:/user";
    }

    @GetMapping("/profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("user", user);

        return "profile";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/changeUserList")
    public String changeUserList(@RequestParam Map<String, String> form,
                                 @AuthenticationPrincipal User user) {
        List<User> users = userService.findAll();
        boolean authUserNoAccess = false;

        if(form.keySet().contains("set-admin")) {
            for(String key : form.keySet()){
                if(users.contains(userRepo.findByUsername(key))) {
                    User user1 = userRepo.findByUsername(key);

                    user1.getRoles().clear();
                    user1.getRoles().add(Role.valueOf("USER"));
                    user1.getRoles().add(Role.valueOf("ADMIN"));

                    userRepo.save(user1);
                }

            }

        }
        else if(form.keySet().contains("delete-users")) {
            for(String key : form.keySet()){
                if(user.getUsername().equals(userRepo.findByUsername(key).getUsername()))
                    authUserNoAccess = true;

                if(users.contains(userRepo.findByUsername(key))) {
                    userRepo.delete(userRepo.findByUsername(key));

                }

            }
        }
        else if(form.keySet().contains("block-users")) {
            for(String key : form.keySet()){
                if(user.getUsername().equals(userRepo.findByUsername(key).getUsername()))
                    authUserNoAccess = true;

                if(users.contains(userRepo.findByUsername(key))) {
                    User user1 = userRepo.findByUsername(key);

                    user1.getRoles().clear();

                    userRepo.save(user1);
                }

            }
        }
        else if(form.keySet().contains("unblock-users")) {
            for(String key : form.keySet()){
                if(users.contains(userRepo.findByUsername(key))) {
                    User user1 = userRepo.findByUsername(key);

                    user1.getRoles().add(Role.valueOf("USER"));

                    userRepo.save(user1);
                }

            }
        }

        if(authUserNoAccess) {

        }

        return "redirect:/user";
    }

}