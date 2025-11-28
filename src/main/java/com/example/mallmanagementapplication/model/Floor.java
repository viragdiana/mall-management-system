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

    @NotNull
    @PositiveOrZero
    private Integer level;

    @ManyToOne(optional = false)
    @JoinColumn(name = "mall_id")
    private Mall mall;

    @OneToMany(mappedBy = "floor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Shop> shops = new ArrayList<>();

    @OneToMany(mappedBy = "floor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ElectricalAsset> electricalAssets = new ArrayList<>();

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

    public void setShops(List<Shop> shops) { this.shops = shops; }

    public List<ElectricalAsset> getElectricalAssets() { return electricalAssets; }

    public void setElectricalAssets(List<ElectricalAsset> electricalAssets) {
        this.electricalAssets = electricalAssets;
    }

    public List<StaffAssignment> getAssignments() { return assignments; }

    public void setAssignments(List<StaffAssignment> assignments) {
        this.assignments = assignments;
    }
}
