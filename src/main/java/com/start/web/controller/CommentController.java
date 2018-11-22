package com.start.web.controller;

import com.start.web.domain.Comment;
import com.start.web.domain.Message;
import com.start.web.domain.User;
import com.start.web.repos.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Controller
public class CommentController {
    @Autowired
    private CommentRepo commentRepo;

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