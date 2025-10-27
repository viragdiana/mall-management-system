package com.example.mallmanagementapplication.model;

public class StaffAssignment {
    private String id;
    private String staffId;
    private String shift; // "Morning", "Evening", "Night"

    public StaffAssignment() {}

    public StaffAssignment(String id, String staffId, String shift) {
        this.id = id;
        this.staffId = staffId;
        this.shift = shift;
    }

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getStaffId() { return staffId; }
    public void setStaffId(String staffId) { this.staffId = staffId; }

    public String getShift() { return shift; }
    public void setShift(String shift) { this.shift = shift; }
}
