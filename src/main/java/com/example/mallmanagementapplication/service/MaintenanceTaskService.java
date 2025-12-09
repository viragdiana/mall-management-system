package com.example.mallmanagementapplication.service;

import com.example.mallmanagementapplication.model.MaintenanceTask;
import com.example.mallmanagementapplication.repository.FloorRepository;
import com.example.mallmanagementapplication.repository.MaintenanceTaskRepository;
import com.example.mallmanagementapplication.repository.StaffAssignmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MaintenanceTaskService {

    private final MaintenanceTaskRepository repo;
    private final FloorRepository floorRepo;
    private final StaffAssignmentRepository assignmentRepo;

    public MaintenanceTaskService(MaintenanceTaskRepository repo,
                                  FloorRepository floorRepo,
                                  StaffAssignmentRepository assignmentRepo) {
        this.repo = repo;
        this.floorRepo = floorRepo;
        this.assignmentRepo = assignmentRepo;
    }

    // GET ALL
    public List<MaintenanceTask> getAll() {
        return repo.findAll();
    }

    // GET ONE
    public MaintenanceTask getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found: " + id));
    }

    public MaintenanceTask save(MaintenanceTask task) {

        if (task.getDescription() == null || task.getDescription().isBlank()) {
            throw new IllegalStateException("Description cannot be empty!");
        }

        if (task.getStatus() == null) {
            throw new IllegalStateException("Task must have a status!");
        }

        if (task.getFloor() == null || task.getFloor().getId() == null) {
            throw new IllegalStateException("Task must belong to a floor!");
        }

        // VALIDARE REALĂ ÎN DB
        floorRepo.findById(task.getFloor().getId())
                .orElseThrow(() -> new IllegalStateException("Floor does not exist!"));

        if (task.getAssignment() != null) {
            Long assignId = task.getAssignment().getId();

            assignmentRepo.findById(assignId)
                    .orElseThrow(() -> new IllegalStateException("Assignment does not exist!"));
        }

        return repo.save(task);
    }

    // DELETE
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Task not found: " + id);
        }
        repo.deleteById(id);
    }
}
