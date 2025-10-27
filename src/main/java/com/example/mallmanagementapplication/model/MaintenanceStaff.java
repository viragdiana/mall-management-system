package com.example.mallmanagementapplication.model;

import java.util.List;

public class MaintenanceStaff extends Staff {
    private String type; // Electrical, Cleaning, etc.
    private List<MaintenanceTask> tasks;

    public MaintenanceStaff() {}

    public MaintenanceStaff(String id, String name, String type, List<MaintenanceTask> tasks) {
        super(id, name);
        this.type = type;
        this.tasks = tasks;
    }

    // Getters & Setters
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public List<MaintenanceTask> getTasks() { return tasks; }
    public void setTasks(List<MaintenanceTask> tasks) { this.tasks = tasks; }
}
