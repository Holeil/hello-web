package com.start.web.controller;

import com.start.web.domain.Message;
import com.start.web.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/")
    public String greeting(Model model) {
        model.addAttribute("message", messageRepo.findAll());

        return "main";
    }
}