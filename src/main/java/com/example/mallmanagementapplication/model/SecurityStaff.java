package com.example.mallmanagementapplication.model;

public class SecurityStaff extends Staff {
    private String shift; // Morning / Evening / Night

    public SecurityStaff() {}

    public SecurityStaff(String id, String name, String shift) {
        super(id, name);
        this.shift = shift;
    }

    // Getters & Setters
    public String getShift() { return shift; }
    public void setShift(String shift) { this.shift = shift; }
}
