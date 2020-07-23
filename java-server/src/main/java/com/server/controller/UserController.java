package com.server.controller;

import com.server.user.UserEntity;
import com.server.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new UserEntity());

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") UserEntity userForm) {
        userService.save(userForm);

        return "redirect:/signin";
    }

    @GetMapping("/signin")
    public String login(Model model) {
        return "signin";
    }

    @GetMapping("/")
    public String welcome(Model model) {
        return "welcome";
    }

}