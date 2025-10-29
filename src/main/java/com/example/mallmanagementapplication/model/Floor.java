package com.example.mallmanagementapplication.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Etajul dintr-un Mall, care conține magazine, sarcini, echipamente electrice și asignări de personal.
 */
public class Floor implements Identifiable {
    private String id;
    private int number;
    private List<Shop> shops = new ArrayList<>();
    private List<MaintenanceTask> tasks = new ArrayList<>();
    private List<ElectricalAsset> electricals = new ArrayList<>();
    private List<StaffAssignment> assignments = new ArrayList<>();

    public Floor() { }

    public Floor(String id, int number) {
        this.id = id;
        this.number = number;
    }

    @Override
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }

    public List<Shop> getShops() { return shops; }
    public List<MaintenanceTask> getTasks() { return tasks; }
    public List<ElectricalAsset> getElectricals() { return electricals; }
    public List<StaffAssignment> getAssignments() { return assignments; }

    public void addShop(Shop s) { if (s != null) shops.add(s); }
    public void addTask(MaintenanceTask t) { if (t != null) tasks.add(t); }
    public void addElectrical(ElectricalAsset e) { if (e != null) electricals.add(e); }
    public void addAssignment(StaffAssignment a) { if (a != null) assignments.add(a); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Floor)) return false;
        Floor floor = (Floor) o;
        return Objects.equals(id, floor.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}
