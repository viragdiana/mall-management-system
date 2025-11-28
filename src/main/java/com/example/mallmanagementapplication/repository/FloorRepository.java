package com.example.mallmanagementapplication.repository;

import com.example.mallmanagementapplication.model.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FloorRepository extends JpaRepository<Floor, Long> {

    boolean existsByLevelAndMallId(int level, Long mallId);

}
