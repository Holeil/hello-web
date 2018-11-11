package com.start.web.controller;

import com.start.web.domain.Role;
import com.start.web.domain.User;
import com.start.web.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/user")
    public String userList(Model model) {
        model.addAttribute("users", userRepo.findAll());

        return "userList";
    }

    @GetMapping("/user/{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());

        return "userEdit";
    }

    @PostMapping("/changeUser")
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ) {
        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepo.save(user);

       return "redirect:/user";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam("userId") User user ) {
        userRepo.delete(user);

        return "redirect:/user";
    }

    @PostMapping("/blockUser")
    public String blockUser(@RequestParam("userId") User user) {
        user.getRoles().clear();

        userRepo.save(user);

        return "redirect:/user";
    }

    @PostMapping("/unblockUser")
    public String unblockUser(@RequestParam("userId") User user) {
        user.getRoles().add(Role.USER);

        userRepo.save(user);

        return "redirect:/user";
    }
}