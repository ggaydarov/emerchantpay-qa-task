package com.server.controller;

import com.server.employee.EmployeeEntity;
import com.server.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/hr")
    public String hr(Model model) {
        model.addAttribute("list", employeeService.getAll());
        return "hr";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("emp") EmployeeEntity emp){
        employeeService.save(emp);
        return "redirect:/hr";
    }

    @GetMapping("/add")
    public String addEmployee(Model model) {
        return "add-emp";
    }
}
