package com.example.mallmanagementapplication.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Customer implements Identifiable {
    private String id;
    private String name;
    private String currency;
    private List<Purchase> purchases = new ArrayList<>();
    private String email;

    public Customer() { }

    public Customer(String id, String name, String currency, String email) {
        this.id = id;
        this.name = name;
        this.currency = currency;
        this.email = email;
    }

    @Override
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public List<Purchase> getPurchases() { return purchases; }
    public void addPurchase(Purchase p) { if (p != null) purchases.add(p); }

    public String getEmail() { return email; }
    public void setEmail(String email) {this.email = email;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer that = (Customer) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}
