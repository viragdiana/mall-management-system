package com.example.mallmanagementapplication.controller;

import com.example.mallmanagementapplication.model.Shift;
import com.example.mallmanagementapplication.model.StaffAssignment;
import com.example.mallmanagementapplication.service.FloorService;
import com.example.mallmanagementapplication.service.MaintenanceStaffService;
import com.example.mallmanagementapplication.service.StaffAssignmentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
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

    public StaffAssignmentController(
            StaffAssignmentService service,
            FloorService floorService,
            MaintenanceStaffService maintenanceService
    ) {
        this.service = service;
        this.floorService = floorService;
        this.maintenanceService = maintenanceService;
    }

    /* ===================== LIST + FILTER + SORT ===================== */
    @GetMapping
    public String index(
            @RequestParam(required = false) Shift shift,
            @RequestParam(defaultValue = "staff") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            Model model
    ) {
        if (!sortBy.equals("staff") && !sortBy.equals("shift")) {
            sortBy = "staff";
        }

        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy.equals("staff")
                ? "staff.name"
                : "shift").descending()
                : Sort.by(sortBy.equals("staff")
                ? "staff.name"
                : "shift").ascending();

        model.addAttribute(
                "assignments",
                service.getFilteredAndSorted(shift, sort)
        );

        model.addAttribute("shifts", Shift.values());
        model.addAttribute("shift", shift);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);

        return "assignments/index";
    }

    /* ===================== DETAILS ===================== */
    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("assignment", service.getById(id));
        return "assignments/details";
    }

    /* ===================== NEW ===================== */
    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("assignment", new StaffAssignment());
        model.addAttribute("floors", floorService.getAll());
        model.addAttribute("maintenanceStaff", maintenanceService.getAll());
        model.addAttribute("shifts", Shift.values());
        return "assignments/new";
    }

    /* ===================== CREATE ===================== */
    @PostMapping
    public String create(
            @Valid @ModelAttribute("assignment") StaffAssignment assignment,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("floors", floorService.getAll());
            model.addAttribute("maintenanceStaff", maintenanceService.getAll());
            model.addAttribute("shifts", Shift.values());
            return "assignments/new";
        }

        try {
            service.save(assignment);
            return "redirect:/assignments";
        } catch (IllegalStateException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("floors", floorService.getAll());
            model.addAttribute("maintenanceStaff", maintenanceService.getAll());
            model.addAttribute("shifts", Shift.values());
            return "assignments/new";
        }
    }

    /* ===================== EDIT ===================== */
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("assignment", service.getById(id));
        model.addAttribute("floors", floorService.getAll());
        model.addAttribute("maintenanceStaff", maintenanceService.getAll());
        model.addAttribute("shifts", Shift.values());
        return "assignments/edit";
    }

    /* ===================== UPDATE ===================== */
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
            model.addAttribute("shifts", Shift.values());
            return "assignments/edit";
        }

        try {
            StaffAssignment existing = service.getById(id);
            existing.setFloor(updated.getFloor());
            existing.setStaff(updated.getStaff());
            existing.setShift(updated.getShift());

            service.save(existing);
            return "redirect:/assignments";

        } catch (IllegalStateException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("floors", floorService.getAll());
            model.addAttribute("maintenanceStaff", maintenanceService.getAll());
            model.addAttribute("shifts", Shift.values());
            return "assignments/edit";
        }
    }

    /* ===================== DELETE ===================== */
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/assignments";
    }
}