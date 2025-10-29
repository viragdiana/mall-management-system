package com.example.mallmanagementapplication.repository;

import com.example.mallmanagementapplication.model.MaintenanceTask;
import com.example.mallmanagementapplication.model.TaskStatus;
import java.util.List;
import java.util.stream.Collectors;

public class MaintenanceTaskRepository extends InMemoryRepository<MaintenanceTask> {

    public List<MaintenanceTask> findByStatus(TaskStatus status) {
        return store.values().stream()
                .filter(t -> t.getStatus() == status)
                .collect(Collectors.toList());
    }
}