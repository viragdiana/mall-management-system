package com.example.mallmanagementapplication.controller;

import com.example.mallmanagementapplication.model.StaffAssignment;
import com.example.mallmanagementapplication.model.Shift;
import com.example.mallmanagementapplication.service.FloorService;
import com.example.mallmanagementapplication.service.MaintenanceStaffService;
import com.example.mallmanagementapplication.service.SecurityStaffService;
import com.example.mallmanagementapplication.service.StaffAssignmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/assignments")
public class StaffAssignmentController {

    private final StaffAssignmentService assignmentService;
    private final FloorService floorService;
    private final MaintenanceStaffService maintenanceService;
    private final SecurityStaffService securityService;

    public StaffAssignmentController(
            StaffAssignmentService assignmentService,
            FloorService floorService,
            MaintenanceStaffService maintenanceService,
            SecurityStaffService securityService) {

        this.assignmentService = assignmentService;
        this.floorService = floorService;
        this.maintenanceService = maintenanceService;
        this.securityService = securityService;
    }

    // LIST
    @GetMapping
    public String index(Model model) {
        model.addAttribute("assignments", assignmentService.getAllAssignments());
        return "assignments/index";
    }

    // DETAILS
    @GetMapping("/{id}")
    public String details(@PathVariable String id, Model model) {
        model.addAttribute("assignment", assignmentService.getAssignment(id));
        return "assignments/details";
    }

    // FORM
    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("assignment", new StaffAssignment());
        model.addAttribute("floors", floorService.getAllFloors());
        model.addAttribute("maintenanceStaff", maintenanceService.getAll());
        model.addAttribute("securityStaff", securityService.getAll());
        model.addAttribute("shifts", Shift.values());
        return "assignments/form";
    }

    // CREATE
    @PostMapping
    public String create(@ModelAttribute StaffAssignment assignment) {
        assignmentService.createAssignment(assignment);
        return "redirect:/assignments";
    }

    // EDIT FORM
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable String id, Model model) {
        model.addAttribute("assignment", assignmentService.getAssignment(id));
        model.addAttribute("floors", floorService.getAllFloors());
        model.addAttribute("maintenanceStaff", maintenanceService.getAll());
        model.addAttribute("securityStaff", securityService.getAll());
        model.addAttribute("shifts", Shift.values());
        return "assignments/edit";
    }

    // UPDATE
    @PostMapping("/{id}/edit")
    public String update(@PathVariable String id, @ModelAttribute StaffAssignment updated) {
        StaffAssignment existing = assignmentService.getAssignment(id);

        existing.setFloorId(updated.getFloorId());
        existing.setStaffId(updated.getStaffId());
        existing.setShift(updated.getShift());

        assignmentService.createAssignment(existing);
        return "redirect:/assignments";
    }

    // DELETE
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        assignmentService.deleteAssignment(id);
        return "redirect:/assignments";
    }
}
