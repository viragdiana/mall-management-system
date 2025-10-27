package com.example.mallmanagementapplication.model;

import java.util.List;

public class Customer {
    private String id;
    private String name;
    private String email;
    private List<Purchase> purchases;

    public Customer() {}

    public Customer(String id, String name, String email, List<Purchase> purchases) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.purchases = purchases;
    }

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<Purchase> getPurchases() { return purchases; }
    public void setPurchases(List<Purchase> purchases) { this.purchases = purchases; }
}
