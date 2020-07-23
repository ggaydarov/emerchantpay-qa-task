package com.server.controller;

import com.server.employee.EmployeeEntity;
import com.server.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/editemp/{id}")
    public String editEmployee(@PathVariable long id, Model model) {
        EmployeeEntity emp = employeeService.getById(id);
        model.addAttribute("emp", emp);
        return "edit-emp";
    }

    @PostMapping("/editemp")
    public String editEmployee(@ModelAttribute("emp") EmployeeEntity emp) {
        employeeService.edit(emp.getId(), emp);
        return "redirect:/hr";
    }

    @GetMapping("/deleteemp/{id}")
    public String deleteEmployee(@PathVariable long id) {
        employeeService.delete(id);
        return "redirect:/hr";
    }

}
