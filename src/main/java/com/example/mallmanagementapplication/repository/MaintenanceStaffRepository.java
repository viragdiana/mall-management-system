package com.example.mallmanagementapplication.repository;

import com.example.mallmanagementapplication.model.MaintenanceStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceStaffRepository extends JpaRepository<MaintenanceStaff, Long> {
}
