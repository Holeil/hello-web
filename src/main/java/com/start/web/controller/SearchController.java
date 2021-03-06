package com.start.web.controller;

import com.start.web.domain.MessageSearch;
import com.start.web.service.MessageSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;

@Controller
public class SearchController {
    @Autowired
    private MessageSearchService messageSearchService;

    @GetMapping("/search")
    public String search(@RequestParam String text,
                         Model model,
                         @PageableDefault() Pageable pageable) {
        Page<MessageSearch> messages = messageSearchService.search(text, pageable);

        if(messages!= null)
            model.addAttribute("messages", messages.getContent());
        else
            model.addAttribute("messages", new HashSet<>());
        return "search";
    }
} 