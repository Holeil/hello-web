package com.start.web.controller;

import com.start.web.domain.User;
import com.start.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(@AuthenticationPrincipal User user) {
        if(user != null)
            return "redirect:/";
        else
            return "registration";
    }

    @GetMapping("/login")
    public String login(@AuthenticationPrincipal User user) {
        if(user != null)
            return "redirect:/";
        else
            return "login";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam("password2") String passwordConfirmation,
                          @Valid User user,
                          BindingResult bindingResult,
                          Model model) {
        boolean isConfirmEmpty = StringUtils.isEmpty(passwordConfirmation);

        boolean key = false;

        if(isConfirmEmpty) {
            model.addAttribute("password2Error", "Пароль подтверждения не может быть пустым!");
            key = true;
        }

        if (user.getPassword() != null && !user.getPassword().equals(passwordConfirmation)) {
            model.addAttribute("passwordError", "Разные пароли!");
            key = true;
        }
        else if(StringUtils.containsWhitespace(user.getPassword())) {
            model.addAttribute("passwordError", "Пароль не должен содержать пробелы!");
            key = true;
        }
        else if(user.getPassword().length() > 16 || user.getPassword().length() < 6) {
            model.addAttribute("passwordError", "Пароь должен быть длиной 6-16 символов!");
            key = true;
        }

        if(StringUtils.containsWhitespace(user.getUsername())) {
            model.addAttribute("usernameError", "Имя пользователя не должно содержать пробелы!");
            key = true;
        }
        else if(user.getPassword().length() > 16 || user.getPassword().length() < 4) {
            model.addAttribute("usernameError", "Имя пользователя должно быть длиной 4-16 символов!");
            key = true;
        }

        if (isConfirmEmpty || bindingResult.hasErrors() || key) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errors);

            return "registration";
        }

        if (!userService.addUser(user)) {
            model.addAttribute("usernameError", "Пользователь с таким именем существует!");
            return "registration";
        }

        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(@PathVariable String code,
                           Model model) {
        boolean isActivated = userService.activateUser(code);

        if (isActivated) {
            model.addAttribute("messageType", "success");
            model.addAttribute("message", "User successfully activated");
        } else {
            model.addAttribute("messageType", "danger");
            model.addAttribute("message", "Activation code is not found!");
        }

        return "login";
    }
}