package com.example.mallmanagementapplication.controller;

import com.example.mallmanagementapplication.model.SecurityStaff;
import com.example.mallmanagementapplication.service.SecurityStaffService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/security-staff")
public class SecurityStaffController {

    private final SecurityStaffService service;

    public SecurityStaffController(SecurityStaffService service) {
        this.service = service;
    }

    // LIST
    @GetMapping
    public String index(Model model) {
        model.addAttribute("staffList", service.getAll());
        return "staff/security/index";
    }

    // DETAILS
    @GetMapping("/{id}")
    public String details(@PathVariable String id, Model model) {
        model.addAttribute("staff", service.get(id));
        return "staff/security/details";
    }

    // CREATE FORM
    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("staff", new SecurityStaff());
        return "staff/security/form";
    }

    // CREATE
    @PostMapping
    public String create(@ModelAttribute SecurityStaff staff) {
        service.add(staff);
        return "redirect:/security-staff";
    }

    // EDIT FORM
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable String id, Model model) {
        model.addAttribute("staff", service.get(id));
        return "staff/security/edit";
    }

    // UPDATE
    @PostMapping("/{id}/edit")
    public String update(@PathVariable String id, @ModelAttribute SecurityStaff updated) {
        SecurityStaff existing = service.get(id);

        existing.setName(updated.getName());
        existing.setBadgeNo(updated.getBadgeNo());

        service.add(existing);
        return "redirect:/security-staff";
    }

    // DELETE
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.delete(id);
        return "redirect:/security-staff";
    }
}
