package com.example.mallmanagementapplication.service;

import com.example.mallmanagementapplication.model.Floor;
import com.example.mallmanagementapplication.model.MaintenanceTask;
import com.example.mallmanagementapplication.repository.MaintenanceTaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaintenanceTaskService {

    private final MaintenanceTaskRepository repo;

    public MaintenanceTaskService(MaintenanceTaskRepository repo) {
        this.repo = repo;
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

    // SAVE / UPDATE
    public MaintenanceTask save(MaintenanceTask task) {

        // VALIDARE 1 — descriere
        if (task.getDescription() == null || task.getDescription().isBlank()) {
            throw new IllegalStateException("Task description cannot be empty");
        }

        // VALIDARE 2 — status (nu poate fi null)
        if (task.getStatus() == null) {
            throw new IllegalStateException("Task status is required");
        }

        // VALIDARE 3 — floor nu poate fi null
        if (task.getFloor() == null) {
            throw new IllegalStateException("Task must be assigned to a floor");
        }

        // VALIDARE 4 — dacă are assignment, assignment.floor trebuie să fie același
        if (task.getAssignment() != null) {
            Floor f1 = task.getFloor();
            Floor f2 = task.getAssignment().getFloor();

            if (!f1.getId().equals(f2.getId())) {
                throw new IllegalStateException(
                        "Task and assignment must belong to the same floor!"
                );
            }
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
