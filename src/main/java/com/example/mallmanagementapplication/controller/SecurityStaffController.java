package com.example.mallmanagementapplication.controller;

import com.example.mallmanagementapplication.model.SecurityStaff;
import com.example.mallmanagementapplication.service.SecurityStaffService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.mallmanagementapplication.repository.StaffAssignmentRepository;

@Controller
@RequestMapping("/security-staff")
public class SecurityStaffController {

    private final SecurityStaffService service;
    private final StaffAssignmentRepository assignmentRepo;

    public SecurityStaffController(
            SecurityStaffService service,
            StaffAssignmentRepository assignmentRepo
    ) {
        this.service = service;
        this.assignmentRepo = assignmentRepo;
    }


    @GetMapping
    public String index(Model model) {
        model.addAttribute("staffList", service.getAll());
        return "security/index";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {

        SecurityStaff staff = service.getById(id);

        var assignments = assignmentRepo.findByStaffId(id);

        model.addAttribute("staff", staff);
        model.addAttribute("assignments", assignments);

        return "security/details";
    }


    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("staff", new SecurityStaff());
        return "security/new";
    }

    @PostMapping
    public String create(
            @Valid @ModelAttribute("staff") SecurityStaff staff,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "security/new";
        }

        service.save(staff);
        return "redirect:/security-staff";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("staff", service.getById(id));
        return "security/edit";
    }

    @PostMapping("/{id}/edit")
    public String update(
            @PathVariable Long id,
            @Valid @ModelAttribute("staff") SecurityStaff updated,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "security/edit";
        }

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
