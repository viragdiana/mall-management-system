package com.example.mallmanagementapplication.service;

import com.example.mallmanagementapplication.model.MaintenanceTask;
import com.example.mallmanagementapplication.model.TaskStatus;
import com.example.mallmanagementapplication.repository.MaintenanceTaskRepository;
import com.example.mallmanagementapplication.repository.StaffAssignmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.mallmanagementapplication.service.Validation.requireExists;

@Service
public class MaintenanceTaskService {

    private final MaintenanceTaskRepository taskRepo;
    private final StaffAssignmentRepository assignmentRepo;

    public MaintenanceTaskService(MaintenanceTaskRepository taskRepo,
                                  StaffAssignmentRepository assignmentRepo) {
        this.taskRepo = taskRepo;
        this.assignmentRepo = assignmentRepo;
    }

    public void addTask(MaintenanceTask task) {
        if (task.getAssignmentId() != null) {
            requireExists(assignmentRepo, task.getAssignmentId(), "StaffAssignment");
        }
        taskRepo.save(task);
    }

    public MaintenanceTask getTask(String id) {
        return requireExists(taskRepo, id, "Task");
    }

    public List<MaintenanceTask> getAllTasks() {
        return taskRepo.findAll();
    }

    public void deleteTask(String id) {
        requireExists(taskRepo, id, "Task");
        taskRepo.delete(id);
    }

    public List<MaintenanceTask> getTasksByStatus(TaskStatus status) {
        return taskRepo.findAll().stream()
                .filter(t -> t.getStatus() == status)
                .toList();
    }
}
