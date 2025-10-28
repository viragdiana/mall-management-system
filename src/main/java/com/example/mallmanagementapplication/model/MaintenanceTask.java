package com.example.mallmanagementapplication.model;

public class MaintenanceTask {
    private String id;
    private String description;
    private TaskStatus status;

    public MaintenanceTask() {}

    public MaintenanceTask(String id, String description, TaskStatus status) {
        this.id = id;
        this.description = description;
        this.status = status;
    }

    // --- Getters & Setters ---
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MaintenanceTask{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
