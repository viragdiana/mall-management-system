package com.example.mallmanagementapplication.repository;

import com.example.mallmanagementapplication.model.ElectricalAsset;
import com.example.mallmanagementapplication.model.AssetStatus;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElectricalAssetRepository extends JpaRepository<ElectricalAsset, Long> {

    // filter by status + sort
    List<ElectricalAsset> findByStatus(AssetStatus status, Sort sort);
}