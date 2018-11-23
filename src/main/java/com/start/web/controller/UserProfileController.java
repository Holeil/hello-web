package com.start.web.controller;

import com.start.web.domain.Message;
import com.start.web.domain.User;
import com.start.web.domain.util.UserHelper;
import com.start.web.repos.MessageRepo;
import com.start.web.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public String userProfilePage(@AuthenticationPrincipal User authUser,
                                  @PathVariable String username,
                                  Model model,
                                  @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        User user = userRepo.findByUsername(username);

        Page<Message> page = messageRepo.findByAuthor(user, pageable);

        boolean access;

        if(!authUser.isRole("ADMIN") && (authUser.isRole("BLOCKED") || !UserHelper.compareTwoUsers(authUser, user))) {
            access = false;
        }
        else {
            access = true;
        }
        
        model.addAttribute("user", user);
        model.addAttribute("url", "/profile/" + username);
        model.addAttribute("page", page);
        model.addAttribute("access", access);

        return "profile";
    }
} 