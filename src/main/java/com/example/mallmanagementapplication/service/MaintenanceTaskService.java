package com.example.mallmanagementapplication.service;

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

    public List<MaintenanceTask> getAll() {
        return repo.findAll();
    }

    public MaintenanceTask getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found: " + id));
    }

    public MaintenanceTask save(MaintenanceTask task) {

        if (task.getDescription() == null || task.getDescription().isBlank()) {
            throw new IllegalStateException("Task description cannot be empty");
        }

        return repo.save(task);
    }

    public void delete(Long id) {
        if (!repo.existsById(id))
            throw new EntityNotFoundException("Task not found: " + id);

        repo.deleteById(id);
    }
}
