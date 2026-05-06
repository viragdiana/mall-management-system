package com.example.mallmanagementapplication.controller;

import com.example.mallmanagementapplication.model.MaintenanceTask;
import com.example.mallmanagementapplication.model.TaskStatus;
import com.example.mallmanagementapplication.service.FloorService;
import com.example.mallmanagementapplication.service.MaintenanceTaskService;
import com.example.mallmanagementapplication.service.StaffAssignmentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class MaintenanceTaskController {

    private final MaintenanceTaskService taskService;
    private final StaffAssignmentService assignmentService;
    private final FloorService floorService;

    public MaintenanceTaskController(
            MaintenanceTaskService taskService,
            StaffAssignmentService assignmentService,
            FloorService floorService
    ) {
        this.taskService = taskService;
        this.assignmentService = assignmentService;
        this.floorService = floorService;
    }

    /* ===================== LIST + FILTER + SORT ===================== */
    @GetMapping
    public String index(
            @RequestParam(required = false) Long floorId,
            @RequestParam(defaultValue = "status") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            Model model
    ) {
        if (!sortBy.equals("status") && !sortBy.equals("floor")) {
            sortBy = "status";
        }

        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy.equals("floor")
                ? "assignment.floor.level"
                : "status").descending()
                : Sort.by(sortBy.equals("floor")
                ? "assignment.floor.level"
                : "status").ascending();

        model.addAttribute(
                "tasks",
                taskService.getFilteredAndSorted(floorId, sort)
        );

        model.addAttribute("floors", floorService.getAll());
        model.addAttribute("floorId", floorId);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);

        return "tasks/index";
    }

    /* ===================== DETAILS ===================== */
    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("task", taskService.getById(id));
        return "tasks/details";
    }

    /* ===================== NEW ===================== */
    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("task", new MaintenanceTask());
        model.addAttribute("statuses", TaskStatus.values());
        model.addAttribute("assignments", assignmentService.getAll());
        return "tasks/new";
    }

    /* ===================== CREATE ===================== */
    @PostMapping
    public String create(
            @Valid @ModelAttribute("task") MaintenanceTask task,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("statuses", TaskStatus.values());
            model.addAttribute("assignments", assignmentService.getAll());
            return "tasks/new";
        }

        try {
            taskService.save(task);
        } catch (IllegalStateException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("statuses", TaskStatus.values());
            model.addAttribute("assignments", assignmentService.getAll());
            return "tasks/new";
        }

        return "redirect:/tasks";
    }

    /* ===================== EDIT ===================== */
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("task", taskService.getById(id));
        model.addAttribute("statuses", TaskStatus.values());
        model.addAttribute("assignments", assignmentService.getAll());
        return "tasks/edit";
    }

    /* ===================== UPDATE ===================== */
    @PostMapping("/{id}/edit")
    public String update(
            @PathVariable Long id,
            @Valid @ModelAttribute("task") MaintenanceTask updated,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("statuses", TaskStatus.values());
            model.addAttribute("assignments", assignmentService.getAll());
            return "tasks/edit";
        }

        MaintenanceTask existing = taskService.getById(id);
        existing.setDescription(updated.getDescription());
        existing.setStatus(updated.getStatus());
        existing.setAssignment(updated.getAssignment());

        try {
            taskService.save(existing);
        } catch (IllegalStateException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("statuses", TaskStatus.values());
            model.addAttribute("assignments", assignmentService.getAll());
            return "tasks/edit";
        }

        return "redirect:/tasks";
    }

    /* ===================== DELETE ===================== */
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        taskService.delete(id);
        return "redirect:/tasks";
    }
}