package com.example.mallmanagementapplication.service;

import com.example.mallmanagementapplication.model.AssetStatus;
import com.example.mallmanagementapplication.model.ElectricalAsset;
import com.example.mallmanagementapplication.model.Floor;
import com.example.mallmanagementapplication.repository.ElectricalAssetRepository;
import com.example.mallmanagementapplication.repository.FloorRepository;
import com.example.mallmanagementapplication.repository.InMemoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        assetRepo.save(asset);
    }
    /** Atașează activul la un etaj (validare + sincronizare listă pe Floor). */
    public void addAssetToFloor(String floorId, ElectricalAsset asset) {
        // 1) floor valid
        Floor floor = requireExists(floorRepo, floorId, "Floor");

        // 2) dacă asset.floorId e null sau diferit, îl setăm corect
        if (asset.getFloorId() == null || !asset.getFloorId().equals(floorId)) {
            asset.setFloorId(floorId);
        }

        // 3) salvăm
        assetRepo.save(asset);

        // 4) sincronizăm agregatul din Floor
        if (floor.getElectricals().stream().noneMatch(a -> a.getId().equals(asset.getId()))) {
            floor.addElectrical(asset);
        }
    }

    public ElectricalAsset getAsset(String id) {
        return assetRepo.findById(id);
    }

    public List<ElectricalAsset> getAllAssets() {
        return assetRepo.findAll();
    }

    public void deleteAsset(String id) {
        assetRepo.delete(id);
        // sugestie: poți căuta și elimina din Floor.electricals dacă vrei consistență totală
    }
    public List<ElectricalAsset> getAssetsByStatus(AssetStatus status) {
        return assetRepo.findByStatus(status);
    }


}
