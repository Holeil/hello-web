package com.start.web.controller;

import com.start.web.domain.Message;
import com.start.web.domain.User;
import com.start.web.domain.dto.CommentDto;
import com.start.web.domain.util.UserHelper;
import com.start.web.repos.CommentRepo;
import com.start.web.repos.MessageRepo;
import com.start.web.repos.UserRepo;
import com.start.web.service.MessageService;
import org.pegdown.PegDownProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MessageController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private MessageService messageService;

    @GetMapping("/message/{message}")
    public String messagePage(@PathVariable Message message,
                              Model model,
                              @AuthenticationPrincipal User user) {

        List<CommentDto> comments = commentRepo.findByMessage(message, user);


        model.addAttribute("siteTheme", UserHelper.getThemeUser(user));
        model.addAttribute("converter", new PegDownProcessor());
        model.addAttribute("message", message);
        model.addAttribute("comments", comments);

        return "message";
    }

    @GetMapping("/profile/{username}/addmessage")
    public String createMessagePage(@AuthenticationPrincipal User authUser,
                                    @PathVariable String username,
                                    Model model) {
        User user = userRepo.findByUsername(username);

        if(!(authUser.isRole("ADMIN") || UserHelper.compareTwoUsers(authUser, user)) || authUser.isRole("BLOCKED")) {
            return "redirect:/profile/" + username;
        }

        model.addAttribute("siteTheme", UserHelper.getThemeUser(authUser));
        model.addAttribute("user", user);

        return "addmessage";
    }

    @PostMapping("/profile/{username}/addmessage")
    public String createMessage(@PathVariable String username,
                                @RequestParam String title,
                                @RequestParam String specialty,
                                @RequestParam String text,
                                @RequestParam String tag) {
        User user = userRepo.findByUsername(username);

        if(!(user == null || title.equals("") || specialty.equals("") || text.equals("") || tag.equals(""))) {
            Message message = new Message(title, specialty, text, tag, user);

            messageRepo.save(message);
        }

        return "redirect:/profile/" + username;
    }

    @PostMapping("/profile/{username}/deletemessage")
    public String deleteMessage(@AuthenticationPrincipal User authUser,
                                @PathVariable String username,
                                @RequestParam("messageId") Message message) {
        User user = userRepo.findByUsername(username);

        if(!(authUser.isRole("ADMIN") || UserHelper.compareTwoUsers(authUser, user)) || authUser.isRole("BLOCKED")) {
            return "redirect:/profile/" + username;
        }

        messageService.deleteMessage(message);

        return "redirect:/profile/" + username;
    }

    @GetMapping("/profile/{username}/updatemessage{message}")
    public String updateMessagePage(@AuthenticationPrincipal User authUser,
                                    @PathVariable String username,
                                    @PathVariable Message message,
                                    Model model) {
        User user = userRepo.findByUsername(username);

        if(!(authUser.isRole("ADMIN") || UserHelper.compareTwoUsers(authUser, user)) || authUser.isRole("BLOCKED")) {
            return "redirect:/profile/" + username;
        }

        model.addAttribute("siteTheme", UserHelper.getThemeUser(authUser));
        model.addAttribute("message", message);
        model.addAttribute("user", user);

        return "updatemessage";
    }

    @PostMapping("/profile/{username}/updatemessage{message}")
    public String updateMessage(@PathVariable String username,
                                @PathVariable Message message,
                                @RequestParam String title,
                                @RequestParam String specialty,
                                @RequestParam String text,
                                @RequestParam String tag) {
        if(!(title.equals("") || specialty.equals("") || text.equals("") || tag.equals(""))) {
            message.setTitle(title);
            message.setSpecialty(specialty);
            message.setText(text);
            message.setTag(tag);
            message.setDate();

            messageRepo.save(message);
        }

        return "redirect:/profile/" + username;
    }

} 