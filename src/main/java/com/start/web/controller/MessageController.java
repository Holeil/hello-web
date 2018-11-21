package com.start.web.controller;

import com.start.web.domain.Comment;
import com.start.web.domain.Message;
import com.start.web.domain.User;
import com.start.web.repos.CommentRepo;
import com.start.web.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Controller
public class MessageController {
    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private CommentRepo commentRepo;

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

    @GetMapping("/user/{user}/message{message}")
    public String deleteMessage(@PathVariable User user,
                                @PathVariable Message message,
                                Model model) {
        model.addAttribute("message", message);
        model.addAttribute("user", user);

        return "updatemessage";
    }

    @PostMapping("/user/{user}/updatemessage")
    public String updateMessage(@AuthenticationPrincipal User authUser,
                                @PathVariable User user,
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

        if(authUser.isRole("ADMIN")) {
            return "redirect:/user/" + user.getId();
        }
        else return "redirect:/user/profile";
    }

    @GetMapping("/message/{message}")
    public String messagePage(@PathVariable Message message,
                              Model model) {
        model.addAttribute("message", message);
        model.addAttribute("comments", commentRepo.findByMessage(message));

        return "message";
    }

    @PostMapping("/message/{message}/addcomment")
    public String addComment(@PathVariable Message message,
                             @AuthenticationPrincipal User user,
                             @RequestParam String text) {
        String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());

        Comment comment = new Comment(text, date, user, message);

        commentRepo.save(comment);

        return "redirect:/message/" + message.getId();
    }
} 