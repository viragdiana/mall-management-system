package com.example.mallmanagementapplication.controller;

import com.example.mallmanagementapplication.model.MaintenanceTask;
import com.example.mallmanagementapplication.model.TaskStatus;
import com.example.mallmanagementapplication.service.MaintenanceTaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class MaintenanceTaskController {

    private final MaintenanceTaskService service;

    public MaintenanceTaskController(MaintenanceTaskService service) {
        this.service = service;
    }

    // LIST
    @GetMapping
    public String index(Model model) {
        model.addAttribute("tasks", service.getAllTasks());
        return "tasks/index";
    }

    // DETAILS
    @GetMapping("/{id}")
    public String details(@PathVariable String id, Model model) {
        model.addAttribute("task", service.getTask(id));
        return "tasks/details";
    }

    // FORM
    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("task", new MaintenanceTask());
        model.addAttribute("statuses", TaskStatus.values());
        return "tasks/form";
    }

    // CREATE
    @PostMapping
    public String create(@ModelAttribute MaintenanceTask task) {
        service.addTask(task);
        return "redirect:/tasks";
    }

    // EDIT FORM
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable String id, Model model) {
        model.addAttribute("task", service.getTask(id));
        model.addAttribute("statuses", TaskStatus.values());
        return "tasks/edit";
    }

    // UPDATE
    @PostMapping("/{id}/edit")
    public String update(@PathVariable String id, @ModelAttribute MaintenanceTask updated) {
        MaintenanceTask existing = service.getTask(id);

        existing.setDescription(updated.getDescription());
        existing.setStatus(updated.getStatus());
        existing.setAssignmentId(updated.getAssignmentId());

        service.addTask(existing);
        return "redirect:/tasks";
    }

    // DELETE
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.deleteTask(id);
        return "redirect:/tasks";
    }
}
