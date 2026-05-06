package com.example.mallmanagementapplication.controller;

import com.example.mallmanagementapplication.model.MaintenanceStaff;
import com.example.mallmanagementapplication.model.MaintenanceType;
import com.example.mallmanagementapplication.repository.StaffAssignmentRepository;
import com.example.mallmanagementapplication.service.MaintenanceStaffService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/maintenance-staff")
public class MaintenanceStaffController {

    private final MaintenanceStaffService service;
    private final StaffAssignmentRepository staffAssignmentRepository;

    public MaintenanceStaffController(
            MaintenanceStaffService service,
            StaffAssignmentRepository staffAssignmentRepository
    ) {
        this.service = service;
        this.staffAssignmentRepository = staffAssignmentRepository;
    }

    /* ===================== LIST + FILTER + SORT ===================== */
    @GetMapping
    public String index(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            Model model
    ) {
        if (!sortBy.equals("name") && !sortBy.equals("type")) {
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

        return "maintenance/staff/index";
    }

    /* ===================== DETAILS ===================== */
    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        MaintenanceStaff staff = service.getById(id);
        model.addAttribute("staff", staff);
        model.addAttribute(
                "assignments",
                staffAssignmentRepository.findByStaffId(id)
        );
        return "maintenance/staff/details";
    }

    /* ===================== NEW ===================== */
    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("staff", new MaintenanceStaff());
        model.addAttribute("types", MaintenanceType.values());
        return "maintenance/staff/new";
    }

    /* ===================== CREATE ===================== */
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

    /* ===================== EDIT ===================== */
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("staff", service.getById(id));
        model.addAttribute("types", MaintenanceType.values());
        return "maintenance/staff/edit";
    }

    /* ===================== UPDATE ===================== */
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

    /* ===================== DELETE ===================== */
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/maintenance-staff";
    }
}