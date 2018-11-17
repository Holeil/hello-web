package com.start.web.controller;

import com.start.web.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class NoteController {
    @GetMapping("/user/{user}/notecreate")
    public String createNote(@PathVariable User user, Model model) {
        model.addAttribute("user", user);

        return "notecreate";
    }
} 