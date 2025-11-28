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

    @GetMapping
    public String index(Model model) {
        model.addAttribute("staffList", service.getAll());
        return "security/index";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("staff", service.getById(id));
        return "security/details";
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("staff", new SecurityStaff());
        return "security/form";
    }

    @PostMapping
    public String create(@ModelAttribute SecurityStaff staff) {
        service.save(staff);
        return "redirect:/security-staff";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("staff", service.getById(id));
        return "security/edit";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute SecurityStaff updated) {
        SecurityStaff existing = service.getById(id);

        existing.setName(updated.getName());
        existing.setBadgeNo(updated.getBadgeNo());

        service.save(existing);
        return "redirect:/security-staff";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/security-staff";
    }
}
