package com.example.mallmanagementapplication.model;

import java.util.List;

public class Floor {
    private String id;
    private String floorName;
    private List<Shop> shops;

    public Floor() {}

    public Floor(String id, String floorName, List<Shop> shops) {
        this.id = id;
        this.floorName = floorName;
        this.shops = shops;
    }

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getFloorName() { return floorName; }
    public void setFloorName(String floorName) { this.floorName = floorName; }

    public List<Shop> getShops() { return shops; }
    public void setShops(List<Shop> shops) { this.shops = shops; }
}
