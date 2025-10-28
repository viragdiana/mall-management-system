package com.example.mallmanagementapplication.model;

import java.util.List;

public class MaintenanceStaff extends Staff {
    private MaintenanceType type;
    private List<MaintenanceTask> tasks;

    public MaintenanceStaff() {}

    public MaintenanceStaff(String id, String name, MaintenanceType type, List<MaintenanceTask> tasks) {
        super(id, name);
        this.type = type;
        this.tasks = tasks;
    }

    // --- Getters & Setters ---
    public MaintenanceType getType() {
        return type;
    }

    public void setType(MaintenanceType type) {
        this.type = type;
    }

    public List<MaintenanceTask> getTasks() {
        return tasks;
    }

    public void setTasks(List<MaintenanceTask> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "MaintenanceStaff{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", type=" + type +
                ", tasks=" + tasks +
                '}';
    }
}
