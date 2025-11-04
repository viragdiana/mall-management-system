package com.example.mallmanagementapplication.model;

import com.example.mallmanagementapplication.model.TaskStatus;
import java.util.Objects;
import java.util.UUID;

/**
 * Task de mentenanță, legat opțional de o asignare de personal (assignmentId).
 */
public class MaintenanceTask implements Identifiable {
    private String id;
    private String description;
    private TaskStatus status; // PLANNED, ACTIVE, DONE
    private String assignmentId; // poate fi null

    public MaintenanceTask() { this.id = UUID.randomUUID().toString();}

    public MaintenanceTask(/*String id,*/ String description, TaskStatus status) {
        this();
        this.description = description;
        this.status = status;
    }

    @Override
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public TaskStatus getStatus() { return status; }
    public void setStatus(TaskStatus status) { this.status = status; }

    public String getAssignmentId() { return assignmentId; }
    public void setAssignmentId(String assignmentId) { this.assignmentId = assignmentId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MaintenanceTask)) return false;
        MaintenanceTask that = (MaintenanceTask) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}
