package com.example.mallmanagementapplication.controller;

import com.example.mallmanagementapplication.model.SecurityStaff;
import com.example.mallmanagementapplication.repository.StaffAssignmentRepository;
import com.example.mallmanagementapplication.service.SecurityStaffService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    /* ===================== LIST + FILTER + SORT ===================== */
    @GetMapping
    public String index(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            Model model
    ) {
        if (!sortBy.equals("name") && !sortBy.equals("badgeNo")) {
            sortBy = "name";
        }

        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        model.addAttribute(
                "staffList",
                service.getFilteredAndSorted(name, sort)
        );

        model.addAttribute("name", name);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);

        return "security/index";
    }

    /* ===================== DETAILS ===================== */
    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("staff", service.getById(id));
        model.addAttribute("assignments", assignmentRepo.findByStaffId(id));
        return "security/details";
    }

    /* ===================== NEW ===================== */
    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("staff", new SecurityStaff());
        return "security/new";
    }

    /* ===================== CREATE ===================== */
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

    /* ===================== EDIT ===================== */
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("staff", service.getById(id));
        return "security/edit";
    }

    /* ===================== UPDATE ===================== */
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

    /* ===================== DELETE ===================== */
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/security-staff";
    }
}