package com.example.mallmanagementapplication.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Mall – conține mai multe etaje (Floor).
 */
public class Mall implements Identifiable {
    private String id;
    private String name;
    private String city;
    private List<Floor> floors = new ArrayList<>();

    public Mall() { }

    public Mall(String id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    @Override
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public List<Floor> getFloors() { return floors; }
    public void setFloors(List<Floor> floors) { this.floors = floors; }

    public void addFloor(Floor floor) {
        if (floor != null) floors.add(floor);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() {
        return "Mall{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", floors=" + floors.size() +
                '}';
    }
}
