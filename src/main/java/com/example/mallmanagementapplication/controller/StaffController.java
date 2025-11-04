package com.example.mallmanagementapplication.controller;

import com.example.mallmanagementapplication.model.MaintenanceStaff;
import com.example.mallmanagementapplication.model.SecurityStaff;
import com.example.mallmanagementapplication.model.Staff;
import com.example.mallmanagementapplication.model.MaintenanceType;
import com.example.mallmanagementapplication.service.StaffService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/staff")
public class StaffController {

    private final StaffService service;

    public StaffController(StaffService service) {
        this.service = service;
    }

    // GET /staff → list all staff
    @GetMapping
    public String showAllStaff(Model model) {
        model.addAttribute("staffList", service.getAllStaff());
        return "staff/index"; // templates/staff/index.html
    }

    // GET /staff/new → show form
    @GetMapping("/new")
    public String showStaffForm(Model model) {
        model.addAttribute("staff", new MaintenanceStaff()); // default form object
        model.addAttribute("maintenanceTypes", MaintenanceType.values());
        return "staff/form"; // templates/staff/form.html
    }

    // POST /staff → create new staff (Maintenance or Security)
    @PostMapping
    public String addStaff(@RequestParam String type,
                           @RequestParam String name,
                           @RequestParam(required = false) String specialization,
                           @RequestParam(required = false) String badgeNo) {

        Staff staff;
        if (type.equalsIgnoreCase("MAINTENANCE")) {
            MaintenanceStaff m = new MaintenanceStaff();
            m.setName(name);
            m.setType(MaintenanceType.valueOf(specialization));
            staff = m;
        } else {
            SecurityStaff s = new SecurityStaff();
            s.setName(name);
            s.setBadgeNo(badgeNo);
            staff = s;
        }

        service.addStaff(staff);
        return "redirect:/staff";
    }

    // POST /staff/{id}/delete → delete staff
    @PostMapping("/{id}/delete")
    public String deleteStaff(@PathVariable String id) {
        service.deleteStaff(id);
        return "redirect:/staff";
    }
}
