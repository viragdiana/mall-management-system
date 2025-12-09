package com.example.mallmanagementapplication.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "maintenance_tasks")
public class MaintenanceTask implements Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 255)
    private String description;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TaskStatus status;

    // 🔥 LEGĂTURA NOUĂ CU FLOOR
    @ManyToOne(optional = false)
    @JoinColumn(name = "floor_id")
    private Floor floor;

    // Relația existentă cu StaffAssignment
    @ManyToOne
    @JoinColumn(name = "assignment_id")
    private StaffAssignment assignment;

    public MaintenanceTask() {}

    public MaintenanceTask(String description, TaskStatus status, Floor floor) {
        this.description = description;
        this.status = status;
        this.floor = floor;
    }

    @Override
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public TaskStatus getStatus() { return status; }
    public void setStatus(TaskStatus status) { this.status = status; }

    public Floor getFloor() { return floor; }
    public void setFloor(Floor floor) { this.floor = floor; }

    public StaffAssignment getAssignment() { return assignment; }
    public void setAssignment(StaffAssignment assignment) { this.assignment = assignment; }
}
