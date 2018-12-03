package com.start.web.controller;

import com.start.web.domain.Message;
import com.start.web.domain.MessageRate;
import com.start.web.domain.User;
import com.start.web.domain.dto.CommentDto;
import com.start.web.domain.util.UserHelper;
import com.start.web.repos.CommentRepo;
import com.start.web.repos.MessageRateRepo;
import com.start.web.repos.UserRepo;
import com.start.web.service.MessageRateService;
import com.start.web.service.MessageService;
import org.pegdown.PegDownProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Controller
public class MessageController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageRateRepo messageRateRepo;

    @Autowired
    private MessageRateService messageRateService;

    @GetMapping("/message/{message}")
    public String messagePage(@PathVariable Message message,
                              Model model,
                              @AuthenticationPrincipal User user) {

        List<CommentDto> comments = commentRepo.findByMessage(message, user);

        model.addAttribute("language", UserHelper.getLangUser(user));
        model.addAttribute("siteTheme", UserHelper.getThemeUser(user));
        model.addAttribute("converter", new PegDownProcessor());
        model.addAttribute("message", message);
        model.addAttribute("comments", comments);
        model.addAttribute("messageRateRepo", messageRateRepo);
        model.addAttribute("messageRateService", messageRateService);

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

        model.addAttribute("language", UserHelper.getLangUser(authUser));
        model.addAttribute("siteTheme", UserHelper.getThemeUser(authUser));
        model.addAttribute("user", user);

        return "addmessage";
    }

    @PostMapping("/profile/{username}/addmessage")
    public String createMessage(@PathVariable String username,
                                @RequestParam String title,
                                @RequestParam String specialty,
                                @RequestParam String text,
                                @RequestParam String tag,
                                @AuthenticationPrincipal User authUser,
                                Model model) {
        User user = userRepo.findByUsername(username);

        boolean key = false;

        if(title.length() > 100 || title.length() < 3) {
            model.addAttribute("titleError", "Название содержит 3-100 символов");
            key = true;
        }
        if(specialty.length() > 24 || specialty.length() < 5) {
            model.addAttribute("specialtyError", "Специальность содержит 5-100 символов");
            key = true;
        }
        if(text.length() > 2000 || text.length() < 1) {
            model.addAttribute("textError", "Текст содержит 1-2000 символов");
            key = true;
        }
        if(tag.length() > 60 || tag.length() < 3) {
            model.addAttribute("tagError", "Тэги содержит 3-60 символов");
            key = true;
        }

        if(key) {
            model.addAttribute("language", UserHelper.getLangUser(authUser));
            model.addAttribute("siteTheme", UserHelper.getThemeUser(authUser));
            model.addAttribute("user", user);

            return "addmessage";
        }

        Message message = new Message(title, specialty, text, tag, user);

        messageService.saveMessage(message);

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

        model.addAttribute("language", UserHelper.getLangUser(authUser));
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
                                @RequestParam String tag,
                                Model model,
                                @AuthenticationPrincipal User authUser) {
        User user = userRepo.findByUsername(username);

        boolean key = false;

        if(title.length() > 100 || title.length() < 3) {
            model.addAttribute("titleError", "Название содержит 3-100 символов");
            key = true;
        }
        if(specialty.length() > 24 || specialty.length() < 5) {
            model.addAttribute("specialtyError", "Специальность содержит 5-100 символов");
            key = true;
        }
        if(text.length() > 2000 || text.length() < 1) {
            model.addAttribute("textError", "Текст содержит 1-2000 символов");
            key = true;
        }
        if(tag.length() > 60 || tag.length() < 3) {
            model.addAttribute("tagError", "Тэги содержит 3-60 символов");
            key = true;
        }

        if(key) {
            model.addAttribute("language", UserHelper.getLangUser(authUser));
            model.addAttribute("siteTheme", UserHelper.getThemeUser(authUser));
            model.addAttribute("user", user);

            return "updatemessage";
        }

        message.setTitle(title);
        message.setSpecialty(specialty);
        message.setText(text);
        message.setTag(tag);
        message.setDate();

        messageService.saveMessage(message);

        return "redirect:/profile/" + username;
    }

    @PostMapping("/message/{messageId}/rates")
    public String rate(@AuthenticationPrincipal User user,
                       @PathVariable Long messageId,
                       @RequestParam("rate") String rate,
                       RedirectAttributes redirectAttributes,
                       @RequestHeader(required = false) String referer) {
        Iterable<MessageRate> messageRates = messageRateRepo.findByMessageIdAndUserId(messageId, user.getId());

        UriComponents components = UriComponentsBuilder.fromHttpUrl(referer).build();

        components.getQueryParams()
                .entrySet()
                .forEach(pair -> redirectAttributes.addAttribute(pair.getKey(), pair.getValue()));

        for(MessageRate messageRate : messageRates) {
            if(messageRate.getUserId().equals(user.getId())) {
                messageRateService.changeRate(messageRate, Integer.parseInt(rate));

                return "redirect:" + components.getPath();
            }
        }

        messageRateRepo.save(new MessageRate(user.getId(), messageId, Integer.parseInt(rate)));

        return "redirect:" + components.getPath();
    }
} 