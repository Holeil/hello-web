package com.start.web.controller;

import com.start.web.domain.Message;
import com.start.web.domain.User;
import com.start.web.repos.CommentRepo;
import com.start.web.repos.MessageRepo;
import com.start.web.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MessageController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private CommentRepo commentRepo;

    @GetMapping("/message/{message}")
    public String messagePage(@PathVariable Message message,
                              Model model) {
        model.addAttribute("message", message);
        model.addAttribute("comments", commentRepo.findByMessage(message));

        return "message";
    }

    @GetMapping("/profile/{username}/addmessage")
    public String createMessagePage(@PathVariable String username,
                                    Model model) {
        User user = userRepo.findByUsername(username);

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
    public String deleteMessage(@PathVariable String username,
                                @RequestParam("messageId") Message message) {
        commentRepo.deleteAll(commentRepo.findByMessage(message));

        messageRepo.delete(message);

        return "redirect:/profile/" + username;
    }

    @GetMapping("/profile/{username}/message{message}")
    public String updateMessagePage(@PathVariable String username,
                                    @PathVariable Message message,
                                    Model model) {
        User user = userRepo.findByUsername(username);

        model.addAttribute("message", message);
        model.addAttribute("user", user);

        return "updatemessage";
    }

    @PostMapping("/profile/{username}/updatemessage")
    public String updateMessage(@PathVariable String username,
                                @RequestParam("messageId") Message message,
                                @RequestParam String title,
                                @RequestParam String specialty,
                                @RequestParam String text,
                                @RequestParam String tag) {
        if(!(title.equals("") || specialty.equals("") || text.equals("") || tag.equals(""))) {
            message.setTitle(title);
            message.setSpecialty(specialty);
            message.setText(text);
            message.setTag(tag);

            messageRepo.save(message);
        }

        return "redirect:/profile/" + username;
    }

} 