package com.example.mallmanagementapplication.repository;

import com.example.mallmanagementapplication.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    boolean existsByNameAndFloorId(String name, Long floorId);
}
