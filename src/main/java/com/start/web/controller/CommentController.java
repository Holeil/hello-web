package com.start.web.controller;

import com.start.web.domain.*;
import com.start.web.domain.util.UserHelper;
import com.start.web.repos.CommentRepo;
import com.start.web.repos.MessageRateRepo;
import com.start.web.service.CommentService;
import com.start.web.service.MessageRateService;
import com.start.web.service.MessageSearchService;
import org.pegdown.PegDownProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Set;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private MessageSearchService messageSearchService;

    @Autowired
    private MessageRateRepo messageRateRepo;

    @Autowired
    private MessageRateService messageRateService;

    @PostMapping("/message/{message}/addcomment")
    public String addComment(@PathVariable Message message,
                             @AuthenticationPrincipal User user,
                             @RequestParam String text,
                             Model model) {
        if(text.length() > 300 || text.length() < 1) {
            model.addAttribute("commentError", "Комментарий может содержать 1-300 символов");
            model.addAttribute("message", message);
            model.addAttribute("comments", commentService.findByMessage(message, user));
            model.addAttribute("language", UserHelper.getLangUser(user));
            model.addAttribute("siteTheme", UserHelper.getThemeUser(user));
            model.addAttribute("converter", new PegDownProcessor());
            model.addAttribute("messageRateRepo", messageRateRepo);
            model.addAttribute("messageRateService", messageRateService);

            return "message";
        }

        Comment comment = new Comment(text, user, message);

        commentService.saveComment(message, comment);

        MessageSearch ms = messageSearchService.findMessageById(message.getId());

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