package com.server.controller;

import com.server.user.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeControler {

    @GetMapping("/hr")
    public String hr(Model model) {

        return "hr";
    }
}
