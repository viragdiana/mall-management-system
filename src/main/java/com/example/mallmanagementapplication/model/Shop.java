package com.example.mallmanagementapplication.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Shop implements Identifiable {
    private String id;
    private String name;
    private String ownerName;
    private double areaSqm;
    private List<Purchase> purchases = new ArrayList<>();
    private ShopType type;

    public Shop() {
        this.id = UUID.randomUUID().toString();
    }

    public Shop(String id, String name, String ownerName, double areaSqm,  ShopType type) {
        this();
        this.name = name;
        this.ownerName = ownerName;
        this.areaSqm = areaSqm;
        this.type = type;
    }

    @Override
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public double getAreaSqm() { return areaSqm; }
    public void setAreaSqm(double areaSqm) { this.areaSqm = areaSqm; }

    public List<Purchase> getPurchases() { return purchases; }
    public void addPurchase(Purchase p) { if (p != null) purchases.add(p); }

    public ShopType getType() { return type; }
    public void setType(ShopType type) { this.type = type;}
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shop)) return false;
        Shop shop = (Shop) o;
        return Objects.equals(id, shop.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}
