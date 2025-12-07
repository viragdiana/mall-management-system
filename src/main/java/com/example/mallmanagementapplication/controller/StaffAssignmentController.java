package com.example.mallmanagementapplication.controller;

import com.example.mallmanagementapplication.model.Shift;
import com.example.mallmanagementapplication.model.StaffAssignment;
import com.example.mallmanagementapplication.service.FloorService;
import com.example.mallmanagementapplication.service.MaintenanceStaffService;
import com.example.mallmanagementapplication.service.SecurityStaffService;
import com.example.mallmanagementapplication.service.StaffAssignmentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/assignments")
public class StaffAssignmentController {

    private final StaffAssignmentService service;
    private final FloorService floorService;
    private final MaintenanceStaffService maintenanceService;
    private final SecurityStaffService securityService;

    public StaffAssignmentController(StaffAssignmentService service,
                                     FloorService floorService,
                                     MaintenanceStaffService maintenanceService,
                                     SecurityStaffService securityService) {
        this.service = service;
        this.floorService = floorService;
        this.maintenanceService = maintenanceService;
        this.securityService = securityService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("assignments", service.getAll());
        return "assignments/index";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("assignment", service.getById(id));
        return "assignments/details";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("assignment", new StaffAssignment());
        model.addAttribute("floors", floorService.getAll());
        model.addAttribute("maintenanceStaff", maintenanceService.getAll());
        model.addAttribute("securityStaff", securityService.getAll());
        model.addAttribute("shifts", Shift.values());
        return "assignments/new";
    }

    @PostMapping
    public String create(
            @Valid @ModelAttribute("assignment") StaffAssignment assignment,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("floors", floorService.getAll());
            model.addAttribute("maintenanceStaff", maintenanceService.getAll());
            model.addAttribute("securityStaff", securityService.getAll());
            model.addAttribute("shifts", Shift.values());
            return "assignments/new";
        }

        service.save(assignment);
        return "redirect:/assignments";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("assignment", service.getById(id));
        model.addAttribute("floors", floorService.getAll());
        model.addAttribute("maintenanceStaff", maintenanceService.getAll());
        model.addAttribute("securityStaff", securityService.getAll());
        model.addAttribute("shifts", Shift.values());
        return "assignments/edit";
    }

    @PostMapping("/{id}/edit")
    public String update(
            @PathVariable Long id,
            @Valid @ModelAttribute("assignment") StaffAssignment updated,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("floors", floorService.getAll());
            model.addAttribute("maintenanceStaff", maintenanceService.getAll());
            model.addAttribute("securityStaff", securityService.getAll());
            model.addAttribute("shifts", Shift.values());
            return "assignments/edit";
        }

        StaffAssignment existing = service.getById(id);
        existing.setFloor(updated.getFloor());
        existing.setStaff(updated.getStaff());
        existing.setShift(updated.getShift());

        service.save(existing);
        return "redirect:/assignments";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/assignments";
    }
}
