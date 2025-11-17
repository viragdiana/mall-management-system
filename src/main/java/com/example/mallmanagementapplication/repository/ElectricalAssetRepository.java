package com.example.mallmanagementapplication.repository;

import com.example.mallmanagementapplication.model.ElectricalAsset;
import org.springframework.stereotype.Repository;

@Repository
public class ElectricalAssetRepository extends InFileRepository<ElectricalAsset> {

    public ElectricalAssetRepository() {
        super("src/main/resources/data/electrical_assets.json", ElectricalAsset.class);
    }
}
