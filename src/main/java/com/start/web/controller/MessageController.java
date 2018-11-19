package com.start.web.controller;

import com.start.web.domain.Message;
import com.start.web.domain.User;
import com.start.web.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MessageController {
    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/user/{user}/addmessage")
    public String createNote(@PathVariable User user, Model model) {
        model.addAttribute("user", user);

        return "addmessage";
    }

    @PostMapping("/user/{user}/addmessage")
    public String addMessage(@AuthenticationPrincipal User authUser,
                             @PathVariable User user,
                             @RequestParam String title,
                             @RequestParam String specialty,
                             @RequestParam String text,
                             @RequestParam String tag) {
        if(!(user == null || title.equals("") || specialty.equals("") || text.equals("") || tag.equals(""))) {
            Message message = new Message(title, specialty, text, tag, user);

            messageRepo.save(message);
        }

        if(authUser.isRole("ADMIN")) {
            return "redirect:/user/" + user.getId();
        }
        else return "redirect:/user/profile";
    }

    @PostMapping("/user/{user}/deletemessage")
    public String deleteMessage(@AuthenticationPrincipal User authUser,
                                @RequestParam("userId") User user,
                                @RequestParam("id") String id) {
        messageRepo.deleteById(Long.parseLong(id));

        if(authUser.isRole("ADMIN")) {
            return "redirect:/user/" + user.getId();
        }
        else return "redirect:/user/profile";
    }
} 