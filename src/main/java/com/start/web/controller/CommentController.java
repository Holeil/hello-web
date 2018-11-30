package com.start.web.controller;

import com.start.web.domain.Comment;
import com.start.web.domain.Message;
import com.start.web.domain.User;
import com.start.web.repos.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Set;

@Controller
public class CommentController {
    @Autowired
    private CommentRepo commentRepo;

    @PostMapping("/message/{message}/addcomment")
    public String addComment(@PathVariable Message message,
                             @AuthenticationPrincipal User user,
                             @RequestParam String text) {
        Comment comment = new Comment(text, user, message);

        commentRepo.save(comment);

        return "redirect:/message/" + message.getId();
    }

    @GetMapping("/message/{message}/likes{comment}")
    public String like(@AuthenticationPrincipal User user,
                       @PathVariable Comment comment,
                       RedirectAttributes redirectAttributes,
                       @RequestHeader(required = false) String referer) {
        Set<User> likes = comment.getLikes();

        UriComponents components = UriComponentsBuilder.fromHttpUrl(referer).build();

        components.getQueryParams()
                .entrySet()
                .forEach(pair -> redirectAttributes.addAttribute(pair.getKey(), pair.getValue()));

        for(User liker : likes) {
            if(liker.getId().equals(user.getId())) {
                likes.remove(liker);
                return "redirect:" + components.getPath();
            }
        }

        likes.add(user);

        return "redirect:" + components.getPath();


    }
} 