package com.example.mallmanagementapplication.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "staff_assignments")
public class StaffAssignment implements Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Assignment → Floor */
    @ManyToOne(optional = false)
    @JoinColumn(name = "floor_id")
    private Floor floor;

    /** Assignment → MaintenanceStaff */
    @ManyToOne(optional = false)
    @JoinColumn(name = "staff_id")
    private MaintenanceStaff staff;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Shift shift;

    /** 🔥 Assignment → Tasks */
    @OneToMany(mappedBy = "assignment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MaintenanceTask> tasks = new ArrayList<>();

    public StaffAssignment() {}

    public StaffAssignment(Floor floor, MaintenanceStaff staff, Shift shift) {
        this.floor = floor;
        this.staff = staff;
        this.shift = shift;
    }

    @Override
    public Long getId() { return id; }

    public Floor getFloor() { return floor; }
    public void setFloor(Floor floor) { this.floor = floor; }

    public MaintenanceStaff getStaff() { return staff; }
    public void setStaff(MaintenanceStaff staff) { this.staff = staff; }

    public Shift getShift() { return shift; }
    public void setShift(Shift shift) { this.shift = shift; }

    public List<MaintenanceTask> getTasks() { return tasks; }
}
