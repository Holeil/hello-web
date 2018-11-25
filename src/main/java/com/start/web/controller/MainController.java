package com.start.web.controller;

import com.start.web.domain.Message;
import com.start.web.repos.MessageRepo;
import org.pegdown.PegDownProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/")
    public String greeting(Model model,
                           @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Message> page = messageRepo.findAll(pageable);

        model.addAttribute("converter", new PegDownProcessor());
        model.addAttribute("page", page);
        model.addAttribute("url", "/");

        return "main";
    }

}