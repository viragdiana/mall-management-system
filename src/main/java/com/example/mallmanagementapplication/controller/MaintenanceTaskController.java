package com.example.mallmanagementapplication.controller;

import com.example.mallmanagementapplication.model.MaintenanceTask;
import com.example.mallmanagementapplication.model.TaskStatus;
import com.example.mallmanagementapplication.service.MaintenanceTaskService;
import com.example.mallmanagementapplication.service.StaffAssignmentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class MaintenanceTaskController {

    private final MaintenanceTaskService service;
    private final StaffAssignmentService assignmentService;

    public MaintenanceTaskController(MaintenanceTaskService service,
                                     StaffAssignmentService assignmentService) {
        this.service = service;
        this.assignmentService = assignmentService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("tasks", service.getAll());
        return "tasks/index";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("task", service.getById(id));
        return "tasks/details";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("task", new MaintenanceTask());
        model.addAttribute("statuses", TaskStatus.values());
        model.addAttribute("assignments", assignmentService.getAll());
        return "tasks/new";
    }

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
            service.save(task);
        } catch (IllegalStateException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("statuses", TaskStatus.values());
            model.addAttribute("assignments", assignmentService.getAll());
            return "tasks/new";
        }

        return "redirect:/tasks";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("task", service.getById(id));
        model.addAttribute("statuses", TaskStatus.values());
        model.addAttribute("assignments", assignmentService.getAll());
        return "tasks/edit";
    }

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

        MaintenanceTask existing = service.getById(id);
        existing.setDescription(updated.getDescription());
        existing.setStatus(updated.getStatus());
        existing.setAssignment(updated.getAssignment());

        try {
            service.save(existing);
        } catch (IllegalStateException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("statuses", TaskStatus.values());
            model.addAttribute("assignments", assignmentService.getAll());
            return "tasks/edit";
        }

        return "redirect:/tasks";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/tasks";
    }
}
