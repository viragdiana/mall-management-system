package com.example.mallmanagementapplication.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shops")
public class Shop implements Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** NAME: litere, cifre, spații, minim 2 caractere */
    @NotBlank(message = "Shop name cannot be blank")
    @Pattern(
            regexp = "^[A-Za-zÀ-ž0-9\\s]{2,50}$",
            message = "Shop name must contain only letters, digits, spaces (2-50 chars)"
    )
    private String name;

    /** OWNER: litere, cifre, spații */
    @NotBlank(message = "Owner name cannot be blank")
    @Pattern(
            regexp = "^[A-Za-zÀ-ž0-9\\s]{2,50}$",
            message = "Owner name must contain only letters, digits and spaces"
    )
    private String ownerName;

    /** AREA: > 0 */
    @Positive(message = "Area must be a positive number")
    private double areaSqm;

    /** TYPE: cannot be null */
    @NotNull(message = "Shop type must be selected")
    @Enumerated(EnumType.STRING)
    private ShopType type;

    /** FLOOR: cannot be null */
    @ManyToOne(optional = false)
    @JoinColumn(name = "floor_id")
    @NotNull(message = "Shop must belong to a floor")
    private Floor floor;

    /** PURCHASES: one-to-many */
    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Purchase> purchases = new ArrayList<>();


    // --- Constructors ---
    public Shop() {}

    public Shop(String name, String ownerName, double areaSqm, ShopType type, Floor floor) {
        this.name = name;
        this.ownerName = ownerName;
        this.areaSqm = areaSqm;
        this.type = type;
        this.floor = floor;
    }


    // --- Getters & Setters ---

    @Override
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public double getAreaSqm() { return areaSqm; }
    public void setAreaSqm(double areaSqm) { this.areaSqm = areaSqm; }

    public ShopType getType() { return type; }
    public void setType(ShopType type) { this.type = type; }

    public Floor getFloor() { return floor; }
    public void setFloor(Floor floor) { this.floor = floor; }

    public List<Purchase> getPurchases() { return purchases; }
    public void setPurchases(List<Purchase> purchases) { this.purchases = purchases; }
}
