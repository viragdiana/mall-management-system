package com.example.mallmanagementapplication.controller;

import com.example.mallmanagementapplication.model.AssetStatus;
import com.example.mallmanagementapplication.model.ElectricalAsset;
import com.example.mallmanagementapplication.service.ElectricalAssetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/electrical-assets")
public class ElectricalAssetController {

    private final ElectricalAssetService service;

    public ElectricalAssetController(ElectricalAssetService service) {
        this.service = service;
    }

    @GetMapping
    public List<ElectricalAsset> getAllAssets() {
        return service.getAllAssets();
    }

    @GetMapping("/{id}")
    public ElectricalAsset getAsset(@PathVariable String id) {
        return service.getAsset(id);
    }

    @PostMapping("/{floorId}")
    public String addAssetToFloor(@PathVariable String floorId, @RequestBody ElectricalAsset asset) {
        service.addAssetToFloor(floorId, asset);
        return "Asset added to floor successfully.";
    }

    @GetMapping("/status/{status}")
    public List<ElectricalAsset> getAssetsByStatus(@PathVariable AssetStatus status) {
        return service.getAssetsByStatus(status);
    }

    @DeleteMapping("/{id}")
    public void deleteAsset(@PathVariable String id) {
        service.deleteAsset(id);
    }
}