package com.example.mallmanagementapplication.controller;

import com.example.mallmanagementapplication.model.Staff;
import com.example.mallmanagementapplication.service.StaffService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/staff")
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @PostMapping
    public void addStaff(@RequestBody Staff staff) {
        staffService.addStaff(staff);
    }

    @GetMapping
    public List<Staff> getAllStaff() {
        return staffService.getAllStaff();
    }

    @GetMapping("/{id}")
    public Optional<Staff> getStaffById(@PathVariable String id) {
        return staffService.getStaffById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteStaff(@PathVariable String id) {
        staffService.deleteStaff(id);
    }
}
