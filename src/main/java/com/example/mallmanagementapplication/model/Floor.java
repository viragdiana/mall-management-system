package com.example.mallmanagementapplication.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "floors")
public class Floor implements Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Floor level cannot be null")
    @PositiveOrZero(message = "Floor level must be zero or positive")
    private Integer level;

    @ManyToOne(optional = false)
    @JoinColumn(name = "mall_id")
    private Mall mall;

    // Shops on this floor
    @OneToMany(mappedBy = "floor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Shop> shops = new ArrayList<>();

    // Electrical assets on this floor
    @OneToMany(mappedBy = "floor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ElectricalAsset> electricalAssets = new ArrayList<>();

    // Staff assignments on this floor
    @OneToMany(mappedBy = "floor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StaffAssignment> assignments = new ArrayList<>();

    public Floor() {}

    public Floor(Integer level, Mall mall) {
        this.level = level;
        this.mall = mall;
    }

    @Override
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Integer getLevel() { return level; }

    public void setLevel(Integer level) { this.level = level; }

    public Mall getMall() { return mall; }

    public void setMall(Mall mall) { this.mall = mall; }

    public List<Shop> getShops() { return shops; }

    public List<ElectricalAsset> getElectricalAssets() { return electricalAssets; }

    public List<StaffAssignment> getAssignments() { return assignments; }
}
