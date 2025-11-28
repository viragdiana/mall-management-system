package com.example.mallmanagementapplication.repository;

import com.example.mallmanagementapplication.model.ElectricalAsset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectricalAssetRepository extends JpaRepository<ElectricalAsset, Long> {
}
