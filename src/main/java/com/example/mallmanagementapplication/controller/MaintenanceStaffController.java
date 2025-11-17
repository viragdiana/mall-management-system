package com.example.mallmanagementapplication.controller;

import com.example.mallmanagementapplication.model.MaintenanceStaff;
import com.example.mallmanagementapplication.model.MaintenanceType;
import com.example.mallmanagementapplication.service.MaintenanceStaffService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/maintenance-staff")
public class MaintenanceStaffController {

    private final MaintenanceStaffService service;

    public MaintenanceStaffController(MaintenanceStaffService service) {
        this.service = service;
    }

    // LIST
    @GetMapping
    public String index(Model model) {
        model.addAttribute("staffList", service.getAll());
        return "staff/maintenance/index";
    }

    // DETAILS
    @GetMapping("/{id}")
    public String details(@PathVariable String id, Model model) {
        model.addAttribute("staff", service.get(id));
        return "staff/maintenance/details";
    }

    // CREATE FORM
    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("staff", new MaintenanceStaff());
        model.addAttribute("types", MaintenanceType.values());
        return "staff/maintenance/form";
    }

    // CREATE
    @PostMapping
    public String create(@ModelAttribute MaintenanceStaff staff) {
        service.add(staff);
        return "redirect:/maintenance-staff";
    }

    // EDIT FORM
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable String id, Model model) {
        model.addAttribute("staff", service.get(id));
        model.addAttribute("types", MaintenanceType.values());
        return "staff/maintenance/edit";
    }

    // UPDATE
    @PostMapping("/{id}/edit")
    public String update(@PathVariable String id, @ModelAttribute MaintenanceStaff updated) {
        MaintenanceStaff existing = service.get(id);

        existing.setName(updated.getName());
        existing.setType(updated.getType());

        service.add(existing);
        return "redirect:/maintenance-staff";
    }

    // DELETE
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.delete(id);
        return "redirect:/maintenance-staff";
    }
}
