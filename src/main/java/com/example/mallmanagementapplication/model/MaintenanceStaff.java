package com.example.mallmanagementapplication.model;

import jakarta.persistence.*;

@Entity
@Table(name = "maintenance_staff")
public class MaintenanceStaff extends Staff {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MaintenanceType type;

    public MaintenanceStaff() {}

    public MaintenanceStaff(String name, MaintenanceType type) {
        super(name);
        this.type = type;
    }

    public MaintenanceType getType() { return type; }

    public void setType(MaintenanceType type) { this.type = type; }
}
