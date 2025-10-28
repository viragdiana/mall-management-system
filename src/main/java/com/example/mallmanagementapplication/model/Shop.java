package com.example.mallmanagementapplication.model;
import java.util.List;


public class Shop {
    private String id;
    private String name;
    private ShopType type;
    private List<Purchase> purchases;

    public Shop() {}

    public Shop(String id, String name, ShopType type, List<Purchase> purchases) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.purchases = purchases;
    }

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public ShopType getType() { return type; }
    public void setType(ShopType type) { this.type = type; }

    public List<Purchase> getPurchases() { return purchases; }
    public void setPurchases(List<Purchase> purchases) { this.purchases = purchases; }
}
