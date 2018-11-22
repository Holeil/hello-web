package com.start.web.controller;

import com.start.web.domain.Message;
import com.start.web.domain.User;
import com.start.web.repos.MessageRepo;
import com.start.web.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserProfileController {
    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/profile")
    public String profilePage(@AuthenticationPrincipal User user) {

        return "redirect:/profile/" + user.getUsername();
    }

    @GetMapping("/profile/{username}")
    public String userProfilePage(@PathVariable String username,
                                  Model model
    ) {
        User user = userRepo.findByUsername(username);

        Iterable<Message> messages = messageRepo.findByAuthor(user);

        model.addAttribute("user", user);
        model.addAttribute("messages", messages);

        return "profile";
    }
} 