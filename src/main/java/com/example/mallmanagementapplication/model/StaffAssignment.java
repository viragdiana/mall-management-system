package com.example.mallmanagementapplication.model;

import com.example.mallmanagementapplication.model.Shift;
import java.util.Objects;

/**
 * Asociază un angajat (Staff) cu un etaj (Floor) într-un anumit schimb.
 */
public class StaffAssignment implements Identifiable {
    private String id;
    private String floorId;
    private String staffId;
    private Shift shift;

    public StaffAssignment() { }

    public StaffAssignment(String id, String floorId, String staffId, Shift shift) {
        this.id = id;
        this.floorId = floorId;
        this.staffId = staffId;
        this.shift = shift;
    }

    @Override
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getFloorId() { return floorId; }
    public void setFloorId(String floorId) { this.floorId = floorId; }

    public String getStaffId() { return staffId; }
    public void setStaffId(String staffId) { this.staffId = staffId; }

    public Shift getShift() { return shift; }
    public void setShift(Shift shift) { this.shift = shift; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StaffAssignment)) return false;
        StaffAssignment that = (StaffAssignment) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}
