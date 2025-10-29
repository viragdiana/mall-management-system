package com.example.mallmanagementapplication.repository;

import com.example.mallmanagementapplication.model.ElectricalAsset;
import com.example.mallmanagementapplication.model.AssetStatus;
import java.util.List;
import java.util.stream.Collectors;

public class ElectricalAssetRepository extends InMemoryRepository<ElectricalAsset> {
    public List<ElectricalAsset> findByStatus(AssetStatus status) {
        return store.values().stream()
                .filter(a -> a.getStatus() == status)
                .collect(Collectors.toList());
    }
}
