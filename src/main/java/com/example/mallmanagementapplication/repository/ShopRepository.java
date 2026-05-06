package com.example.mallmanagementapplication.repository;

import com.example.mallmanagementapplication.model.Shop;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

    List<Shop> findByNameContainingIgnoreCase(
            String name,
            Sort sort
    );
}