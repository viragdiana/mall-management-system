package com.example.mallmanagementapplication.model;

import com.example.mallmanagementapplication.model.MaintenanceType;
import java.util.ArrayList;
import java.util.List;

/**
 * Personal de mentenanță – poate fi Electric sau Cleaning.
 */
public class MaintenanceStaff extends Staff {
    private List<StaffAssignment> assignments = new ArrayList<>();
    private MaintenanceType type; // ELECTRICAL, CLEANING

    public MaintenanceStaff() { }

    public MaintenanceStaff(String id, String name, MaintenanceType type) {
        super(id, name);
        this.type = type;
    }

    public List<StaffAssignment> getAssignments() { return assignments; }
    public void setAssignments(List<StaffAssignment> assignments) { this.assignments = assignments; }

    public void addAssignment(StaffAssignment a) {
        if (a != null) assignments.add(a);
    }

    public MaintenanceType getType() { return type; }
    public void setType(MaintenanceType type) { this.type = type; }
}
