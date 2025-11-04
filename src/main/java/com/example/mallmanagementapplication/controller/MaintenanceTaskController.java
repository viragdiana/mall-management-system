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

    // ✅ GET /tasks → show all tasks
    @GetMapping
    public String showAllTasks(Model model) {
        model.addAttribute("tasks", service.getAllTasks());
        return "tasks/index"; // points to templates/tasks/index.html
    }

    // ✅ GET /tasks/new → show the form for adding a new task
    @GetMapping("/new")
    public String showTaskForm(Model model) {
        model.addAttribute("task", new MaintenanceTask());
        model.addAttribute("statuses", TaskStatus.values()); // enum values
        return "tasks/form"; // templates/tasks/form.html
    }

    // ✅ POST /tasks → create a new task
    @PostMapping
    public String addTask(@ModelAttribute MaintenanceTask task) {
        service.addTask(task);
        return "redirect:/tasks";
    }

    // ✅ POST /tasks/{id}/delete → delete a task
    @PostMapping("/{id}/delete")
    public String deleteTask(@PathVariable String id) {
        service.deleteTask(id);
        return "redirect:/tasks";
    }
}
