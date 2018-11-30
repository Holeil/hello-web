package com.start.web.controller;

import com.start.web.domain.Message;
import com.start.web.domain.User;
import com.start.web.domain.util.UserHelper;
import com.start.web.repos.MessageRepo;
import com.start.web.repos.UserRepo;
import com.start.web.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@Controller
public class UserProfileController {
    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MessageService messageService;

    @GetMapping("/profile")
    public String profilePage(@AuthenticationPrincipal User user,
                              Model model) {
        model.addAttribute("language", UserHelper.getLangUser(user));
        model.addAttribute("siteTheme", UserHelper.getThemeUser(user));

        return "redirect:/profile/" + user.getUsername();
    }

    @GetMapping("/profile/{username}")
    public String userProfilePage(@AuthenticationPrincipal User authUser,
                                  @PathVariable String username,
                                  Model model
    ) {
        User user = userRepo.findByUsername(username);

        Iterable<Message> messages = messageRepo.findByAuthor(user);


        boolean access;

        if(!authUser.isRole("ADMIN") && (authUser.isRole("BLOCKED") || !UserHelper.compareTwoUsers(authUser, user))) {
            access = false;
        }
        else {
            access = true;
        }

        model.addAttribute("language", UserHelper.getLangUser(authUser));
        model.addAttribute("siteTheme", UserHelper.getThemeUser(authUser));
        model.addAttribute("user", user);
        model.addAttribute("messages", messages);
        model.addAttribute("access", access);

        return "profile";
    }

    @PostMapping("/profile/{username}/changeuserinfo")
    public String changeUserInfo(@RequestParam String userInfo,
                                 @PathVariable String username) {
        User user = userRepo.findByUsername(username);

        userInfo = userInfo.replaceAll("\n|\r\n", "");

        if(userInfo.equals(""))
            userInfo = null;

        user.setInfo(userInfo);

        userRepo.save(user);

        return "redirect:/profile/" + username;
    }

    @PostMapping("/profile/{username}/sort")
    public String sorterMessages(@AuthenticationPrincipal User authUser,
                                @PathVariable String username,
                                @RequestParam Map<String, String> form,
                                Model model) {
        User user = userRepo.findByUsername(username);

        Iterable<Message> messages;

        if(form.keySet().contains("byTitle")) {
            messages = messageRepo.sortAuthorMessageByTitle(user);

        }
        else if(form.keySet().contains("byDate")) {
            messages = messageRepo.sortAuthorMessageByDate(user);

        }
        else {
            messages = messageRepo.findByAuthor(user);

        }

        boolean access;

        if(!authUser.isRole("ADMIN") && (authUser.isRole("BLOCKED") || !UserHelper.compareTwoUsers(authUser, user))) {
            access = false;
        }
        else {
            access = true;
        }

        model.addAttribute("language", UserHelper.getLangUser(authUser));
        model.addAttribute("siteTheme", UserHelper.getThemeUser(authUser));
        model.addAttribute("user", user);
        model.addAttribute("messages", messages);
        model.addAttribute("access", access);

        return "profile";
    }

    @GetMapping("/profile/{username}/filter")
    public String filterMessages(@AuthenticationPrincipal User authUser,
                                 @PathVariable String username,
                                 Model model,
                                 @RequestParam String filter) {
        User user = userRepo.findByUsername(username);

        boolean access;

        if(!authUser.isRole("ADMIN") && (authUser.isRole("BLOCKED") || !UserHelper.compareTwoUsers(authUser, user))) {
            access = false;

            Iterable<Message> messages = messageRepo.findByAuthor(user);

            model.addAttribute("messages", messages);
        }
        else {
            access = true;

            List<Message> messages = messageService.findByFilter(filter, user);

            model.addAttribute("messages", messages);
        }

        model.addAttribute("language", UserHelper.getLangUser(authUser));
        model.addAttribute("siteTheme", UserHelper.getThemeUser(authUser));
        model.addAttribute("user", user);
        model.addAttribute("access", access);

        return "profile";
    }
} 