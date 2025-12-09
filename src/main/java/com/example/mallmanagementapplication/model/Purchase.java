package com.example.mallmanagementapplication.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
@Table(name = "purchases")
public class Purchase implements Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** AMOUNT: trebuie să fie > 0 */
    @NotNull(message = "Amount cannot be null")
    @Positive(message = "Amount must be greater than zero")
    private BigDecimal amount;

    /** CUSTOMER: obligatoriu, nu poate lipsi */
    @NotNull(message = "Customer cannot be null")
    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    /** SHOP: obligatoriu, nu poate lipsi */
    @NotNull(message = "Shop cannot be null")
    @ManyToOne(optional = false)
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    public Purchase() {}

    public Purchase(BigDecimal amount, Customer customer, Shop shop) {
        this.amount = amount;
        this.customer = customer;
        this.shop = shop;
    }

    @Override
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public Shop getShop() { return shop; }
    public void setShop(Shop shop) { this.shop = shop; }
}
