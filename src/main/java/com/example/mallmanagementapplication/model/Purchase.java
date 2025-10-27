package com.example.mallmanagementapplication.model;

public class Purchase {
    private String id;
    private String shopId;
    private String customerId;
    private double amount;

    public Purchase() {}

    public Purchase(String id, String shopId, String customerId, double amount) {
        this.id = id;
        this.shopId = shopId;
        this.customerId = customerId;
        this.amount = amount;
    }

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getShopId() { return shopId; }
    public void setShopId(String shopId) { this.shopId = shopId; }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}
