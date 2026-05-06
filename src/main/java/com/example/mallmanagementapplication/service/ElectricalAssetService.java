package com.example.mallmanagementapplication.service;

import com.example.mallmanagementapplication.model.AssetStatus;
import com.example.mallmanagementapplication.model.ElectricalAsset;
import com.example.mallmanagementapplication.repository.ElectricalAssetRepository;
import com.example.mallmanagementapplication.repository.FloorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElectricalAssetService {

    private final ElectricalAssetRepository repo;
    private final FloorRepository floorRepo;

    public ElectricalAssetService(
            ElectricalAssetRepository repo,
            FloorRepository floorRepo
    ) {
        this.repo = repo;
        this.floorRepo = floorRepo;
    }

    /* ========== LIST ALL (used elsewhere) ========== */
    public List<ElectricalAsset> getAll() {
        return repo.findAll();
    }

    /* ========== FILTER BY STATUS + SORT ========== */
    public List<ElectricalAsset> getFilteredAndSorted(
            AssetStatus status,
            Sort sort
    ) {
        if (status == null) {
            return repo.findAll(sort);
        }
        return repo.findByStatus(status, sort);
    }

    /* ========== GET BY ID ========== */
    public ElectricalAsset getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Electrical asset not found: " + id));
    }

    /* ========== SAVE ========== */
    public ElectricalAsset save(ElectricalAsset asset) {

        if (asset.getFloor() == null || asset.getFloor().getId() == null) {
            throw new IllegalStateException("Electrical asset must belong to a floor!");
        }

        floorRepo.findById(asset.getFloor().getId())
                .orElseThrow(() ->
                        new IllegalStateException("Floor does not exist!"));

        if (asset.getType() == null) {
            throw new IllegalStateException("Asset type is required!");
        }

        if (asset.getStatus() == null) {
            throw new IllegalStateException("Asset status is required!");
        }

        return repo.save(asset);
    }

    /* ========== DELETE ========== */
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Electrical asset not found: " + id);
        }
        repo.deleteById(id);
    }
}