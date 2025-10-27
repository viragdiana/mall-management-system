package com.example.mallmanagementapplication.model;

public class MaintenanceTask {
    private String id;
    private String description;
    private String status; // "Pending", "InProgress", "Done"

    public MaintenanceTask() {}

    public MaintenanceTask(String id, String description, String status) {
        this.id = id;
        this.description = description;
        this.status = status;
    }

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
