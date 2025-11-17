package com.example.mallmanagementapplication.service;

import com.example.mallmanagementapplication.model.ElectricalAsset;
import com.example.mallmanagementapplication.model.AssetStatus;
import com.example.mallmanagementapplication.repository.ElectricalAssetRepository;
import com.example.mallmanagementapplication.repository.FloorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.mallmanagementapplication.service.Validation.requireExists;

@Service
public class ElectricalAssetService {

    private final ElectricalAssetRepository assetRepo;
    private final FloorRepository floorRepo;

    public ElectricalAssetService(ElectricalAssetRepository assetRepo, FloorRepository floorRepo) {
        this.assetRepo = assetRepo;
        this.floorRepo = floorRepo;
    }

    public void addAsset(ElectricalAsset asset) {
        if (asset.getFloorId() != null) {
            requireExists(floorRepo, asset.getFloorId(), "Floor");
        }
        assetRepo.save(asset);
    }

    public ElectricalAsset getAsset(String id) {
        return requireExists(assetRepo, id, "Asset");
    }

    public List<ElectricalAsset> getAllAssets() {
        return assetRepo.findAll();
    }

    public void deleteAsset(String id) {
        requireExists(assetRepo, id, "Asset");
        assetRepo.delete(id);
    }

    public List<ElectricalAsset> getAssetsByStatus(AssetStatus status) {
        return assetRepo.findAll().stream()
                .filter(a -> a.getStatus() == status)
                .toList();
    }
}
