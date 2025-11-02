package com.example.mallmanagementapplication.controller;

import com.example.mallmanagementapplication.model.MaintenanceTask;
import com.example.mallmanagementapplication.model.TaskStatus;
import com.example.mallmanagementapplication.service.MaintenanceTaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maintenance-tasks")
public class MaintenanceTaskController {

    private final MaintenanceTaskService service;

    public MaintenanceTaskController(MaintenanceTaskService service) {
        this.service = service;
    }

    @GetMapping
    public List<MaintenanceTask> getAllTasks() {
        return service.getAllTasks();
    }

    @GetMapping("/{id}")
    public MaintenanceTask getTask(@PathVariable String id) {
        return service.getTask(id);
    }

    @PostMapping
    public String addTask(@RequestBody MaintenanceTask task) {
        service.addTask(task);
        return "Task created successfully.";
    }

    @PostMapping("/floor/{floorId}")
    public String addTaskToFloor(@PathVariable String floorId, @RequestBody MaintenanceTask task) {
        service.addTaskToFloor(floorId, task);
        return "Task added to floor successfully.";
    }

    @GetMapping("/status/{status}")
    public List<MaintenanceTask> getTasksByStatus(@PathVariable TaskStatus status) {
        return service.getTasksByStatus(status);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable String id) {
        service.deleteTask(id);
    }
}
