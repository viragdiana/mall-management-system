package com.example.mallmanagementapplication.repository;

import com.example.mallmanagementapplication.model.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FloorRepository extends JpaRepository<Floor, Long> {

    Optional<Floor> findByLevelAndMall_Id(Integer level, Long mallId);
}
