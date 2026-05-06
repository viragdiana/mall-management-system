package com.example.mallmanagementapplication.repository;

import com.example.mallmanagementapplication.model.MaintenanceTask;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceTaskRepository extends JpaRepository<MaintenanceTask, Long> {

    // filter by floor + sort
    List<MaintenanceTask> findByAssignment_Floor_Id(Long floorId, Sort sort);
}