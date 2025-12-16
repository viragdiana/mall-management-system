package com.example.mallmanagementapplication.service;

import com.example.mallmanagementapplication.model.MaintenanceTask;
import com.example.mallmanagementapplication.repository.MaintenanceTaskRepository;
import com.example.mallmanagementapplication.repository.StaffAssignmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaintenanceTaskService {

    private final MaintenanceTaskRepository repo;
    private final StaffAssignmentRepository assignmentRepo;

    public MaintenanceTaskService(MaintenanceTaskRepository repo,
                                  StaffAssignmentRepository assignmentRepo) {
        this.repo = repo;
        this.assignmentRepo = assignmentRepo;
    }

    public List<MaintenanceTask> getAll() {
        return repo.findAll();
    }

    public MaintenanceTask getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found: " + id));
    }

    public MaintenanceTask save(MaintenanceTask task) {

        if (task.getAssignment() == null || task.getAssignment().getId() == null) {
            throw new IllegalStateException("Task must have an assignment!");
        }

        var assignment = assignmentRepo.findById(task.getAssignment().getId())
                .orElseThrow(() -> new IllegalStateException("Assignment does not exist"));

        if (!(assignment.getStaff() instanceof com.example.mallmanagementapplication.model.MaintenanceStaff)) {
            throw new IllegalStateException(
                    "Tasks can only be assigned to maintenance staff"
            );
        }

        task.setAssignment(assignment);

        return repo.save(task);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Task not found: " + id);
        }
        repo.deleteById(id);
    }
}
