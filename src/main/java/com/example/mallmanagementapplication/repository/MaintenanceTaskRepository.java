package com.example.mallmanagementapplication.repository;

import com.example.mallmanagementapplication.model.MaintenanceTask;
import org.springframework.stereotype.Repository;

@Repository
public class MaintenanceTaskRepository extends InFileRepository<MaintenanceTask> {

    public MaintenanceTaskRepository() {
        super("src/main/resources/data/maintenance_tasks.json", MaintenanceTask.class);
    }
}
