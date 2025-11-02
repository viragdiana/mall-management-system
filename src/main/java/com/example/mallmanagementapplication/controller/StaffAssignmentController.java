package com.example.mallmanagementapplication.controller;

import com.example.mallmanagementapplication.model.Shift;
import com.example.mallmanagementapplication.model.StaffAssignment;
import com.example.mallmanagementapplication.service.StaffAssignmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assignments")
public class StaffAssignmentController {

    private final StaffAssignmentService service;

    public StaffAssignmentController(StaffAssignmentService service) {
        this.service = service;
    }

    @GetMapping
    public List<StaffAssignment> getAllAssignments() {
        return service.getAllAssignments();
    }

    @GetMapping("/{id}")
    public StaffAssignment getAssignment(@PathVariable String id) {
        return service.getAssignment(id);
    }

    @PostMapping
    public String createAssignment(@RequestBody StaffAssignment assignment) {
        service.createAssignment(assignment);
        return "Assignment created successfully.";
    }

    @GetMapping("/shift/{shift}")
    public List<StaffAssignment> getAssignmentsByShift(@PathVariable Shift shift) {
        return service.getAssignmentsByShift(shift);
    }

    @DeleteMapping("/{id}")
    public void deleteAssignment(@PathVariable String id) {
        service.deleteAssignment(id);
    }
}