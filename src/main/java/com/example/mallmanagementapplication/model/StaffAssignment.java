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

    @ManyToOne(optional = false)
    @JoinColumn(name = "floor_id")
    private Floor floor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Shift shift;

    @OneToMany(mappedBy = "assignment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MaintenanceTask> tasks = new ArrayList<>();

    public StaffAssignment() {}

    public StaffAssignment(Floor floor, Staff staff, Shift shift) {
        this.floor = floor;
        this.staff = staff;
        this.shift = shift;
    }

    @Override
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Floor getFloor() { return floor; }

    public void setFloor(Floor floor) { this.floor = floor; }

    public Staff getStaff() { return staff; }

    public void setStaff(Staff staff) { this.staff = staff; }

    public Shift getShift() { return shift; }

    public void setShift(Shift shift) { this.shift = shift; }

    public List<MaintenanceTask> getTasks() { return tasks; }

    public void setTasks(List<MaintenanceTask> tasks) { this.tasks = tasks; }
}
