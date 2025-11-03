package com.example.mallmanagementapplication.model;

import com.example.mallmanagementapplication.model.AssetStatus;
import com.example.mallmanagementapplication.model.ElectricalType;
import java.util.Objects;
import java.util.UUID;

/**
 * Reprezintă echipamente electrice de pe un etaj (lift, AC, lumini etc.).
 */
public class ElectricalAsset implements Identifiable {
    private String id;
    private String floorId;
    private ElectricalType type;
    private AssetStatus status;

    public ElectricalAsset() {
        this.id = UUID.randomUUID().toString();
    }

    public ElectricalAsset(String id, String floorId, ElectricalType type, AssetStatus status) {
        this();
        this.floorId = floorId;
        this.type = type;
        this.status = status;
    }

    @Override
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getFloorId() { return floorId; }
    public void setFloorId(String floorId) { this.floorId = floorId; }

    public ElectricalType getType() { return type; }
    public void setType(ElectricalType type) { this.type = type; }

    public AssetStatus getStatus() { return status; }
    public void setStatus(AssetStatus status) { this.status = status; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ElectricalAsset)) return false;
        ElectricalAsset that = (ElectricalAsset) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}
