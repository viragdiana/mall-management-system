package com.example.mallmanagementapplication.controller;

import com.example.mallmanagementapplication.model.StaffAssignment;
import com.example.mallmanagementapplication.model.Shift;
import com.example.mallmanagementapplication.service.StaffAssignmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/assignments")
public class StaffAssignmentController {

    private final StaffAssignmentService assignmentService;

    public StaffAssignmentController(StaffAssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @GetMapping
    public String showAllAssignments(Model model) {
        model.addAttribute("assignments", assignmentService.getAllAssignments());
        return "assignments/index";
    }

    @GetMapping("/new")
    public String showAssignmentForm(Model model) {
        model.addAttribute("assignment", new StaffAssignment());
        model.addAttribute("shifts", Shift.values());
        return "assignments/form";
    }

    @PostMapping
    public String addAssignment(@ModelAttribute StaffAssignment assignment) {
        assignmentService.createAssignment(assignment);
        return "redirect:/assignments";
    }

    @PostMapping("/{id}/delete")
    public String deleteAssignment(@PathVariable String id) {
        assignmentService.deleteAssignment(id);
        return "redirect:/assignments";
    }
}
