package com.start.web.controller;

import com.start.web.domain.Message;
import com.start.web.domain.User;
import com.start.web.domain.util.UserHelper;
import com.start.web.repos.MessageRateRepo;
import com.start.web.repos.MessageRepo;
import com.start.web.repos.UserRepo;
import com.start.web.service.MessageRateService;
import com.start.web.service.MessageService;
import com.start.web.service.TagService;
import org.pegdown.PegDownProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MessageRateRepo messageRateRepo;

    @Autowired
    private MessageRateService messageRateService;

    @Autowired
    private TagService tagService;

    @Autowired
    private MessageService messageService;

    @GetMapping({"/", "/message"})
    public String greeting(@AuthenticationPrincipal User user,
                           Model model,
                           @PageableDefault(sort = {"date"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Message> page = messageRepo.findAll(pageable);

        model.addAttribute("language", UserHelper.getLangUser(user));
        model.addAttribute("siteTheme", UserHelper.getThemeUser(user));
        model.addAttribute("converter", new PegDownProcessor());
        model.addAttribute("page", page);
        model.addAttribute("url", "/");
        model.addAttribute("messageRateRepo", messageRateRepo);
        model.addAttribute("messageRateService", messageRateService);
        model.addAttribute("tags", tagService.findLastTags(9));
        model.addAttribute("fiveMessages", messageService.getTopMessages(10));

        return "main";
    }


    @GetMapping("/changetheme")
    public String changeTheme(RedirectAttributes redirectAttributes,
                              @RequestHeader(required = false) String referer,
                              @AuthenticationPrincipal User user) {
        UriComponents components = UriComponentsBuilder.fromHttpUrl(referer).build();

        components.getQueryParams()
                .entrySet()
                .forEach(pair -> redirectAttributes.addAttribute(pair.getKey(), pair.getValue()));

        if(user.getSiteDesign().equals("MATHEMATICS")) {
            user.setSiteDesign("HUMANITARIAN");
        }
        else {
            user.setSiteDesign("MATHEMATICS");
        }

        userRepo.save(user);

        return "redirect:" + components.getPath();
    }

    @GetMapping("/changelanguage")
    public String changeLanguage(RedirectAttributes redirectAttributes,
                                 @RequestHeader(required = false) String referer,
                                 @AuthenticationPrincipal User user) {
        UriComponents components = UriComponentsBuilder.fromHttpUrl(referer).build();

        components.getQueryParams()
                .entrySet()
                .forEach(pair -> redirectAttributes.addAttribute(pair.getKey(), pair.getValue()));

        if(user.getLang().equals("RU")) {
            user.setLang("BY");
        }
        else {
            user.setLang("RU");
        }

        userRepo.save(user);

        return "redirect:" + components.getPath();
    }

}