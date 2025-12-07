package com.example.mallmanagementapplication.controller;

import com.example.mallmanagementapplication.model.MaintenanceStaff;
import com.example.mallmanagementapplication.model.MaintenanceType;
import com.example.mallmanagementapplication.service.MaintenanceStaffService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/maintenance-staff")
public class MaintenanceStaffController {

    private final MaintenanceStaffService service;

    public MaintenanceStaffController(MaintenanceStaffService service) {
        this.service = service;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("staffList", service.getAll());
        return "maintenance/staff/index";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("staff", service.getById(id));
        return "maintenance/staff/details";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("staff", new MaintenanceStaff());
        model.addAttribute("types", MaintenanceType.values());
        return "maintenance/staff/new";
    }

    @PostMapping
    public String create(
            @Valid @ModelAttribute("staff") MaintenanceStaff staff,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("types", MaintenanceType.values());
            return "maintenance/staff/new";
        }

        service.save(staff);
        return "redirect:/maintenance-staff";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("staff", service.getById(id));
        model.addAttribute("types", MaintenanceType.values());
        return "maintenance/staff/edit";
    }

    @PostMapping("/{id}/edit")
    public String update(
            @PathVariable Long id,
            @Valid @ModelAttribute("staff") MaintenanceStaff updated,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("types", MaintenanceType.values());
            return "maintenance/staff/edit";
        }

        MaintenanceStaff existing = service.getById(id);
        existing.setName(updated.getName());
        existing.setType(updated.getType());

        service.save(existing);
        return "redirect:/maintenance-staff";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/maintenance-staff";
    }
}
