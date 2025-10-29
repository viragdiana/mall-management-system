package com.example.mallmanagementapplication.model;

import java.util.Objects;

/**
 * Clasă abstractă pentru toți angajații.
 */
public abstract class Staff implements Identifiable {
    private String id;
    private String name;

    protected Staff() { }

    protected Staff(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Staff)) return false;
        Staff staff = (Staff) o;
        return Objects.equals(id, staff.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}
