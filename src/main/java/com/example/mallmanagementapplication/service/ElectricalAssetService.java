package com.example.mallmanagementapplication.service;

import com.example.mallmanagementapplication.model.ElectricalAsset;
import com.example.mallmanagementapplication.repository.ElectricalAssetRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElectricalAssetService {

    private final ElectricalAssetRepository repo;

    public ElectricalAssetService(ElectricalAssetRepository repo) {
        this.repo = repo;
    }

    public List<ElectricalAsset> getAll() {
        return repo.findAll();
    }

    public ElectricalAsset getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Electrical asset not found: " + id));
    }

    public ElectricalAsset save(ElectricalAsset asset) {
        return repo.save(asset);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Electrical asset not found: " + id);
        }
        repo.deleteById(id);
    }
}
