package com.example.mallmanagementapplication.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "electrical_assets")
public class ElectricalAsset implements Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "floor_id")
    private Floor floor;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ElectricalType type;

    @Enumerated(EnumType.STRING)
    @NotNull
    private AssetStatus status;

    public ElectricalAsset() {}

    public ElectricalAsset(Floor floor, ElectricalType type, AssetStatus status) {
        this.floor = floor;
        this.type = type;
        this.status = status;
    }

    @Override
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Floor getFloor() { return floor; }
    public void setFloor(Floor floor) { this.floor = floor; }

    public ElectricalType getType() { return type; }
    public void setType(ElectricalType type) { this.type = type; }

    public AssetStatus getStatus() { return status; }
    public void setStatus(AssetStatus status) { this.status = status; }
}
