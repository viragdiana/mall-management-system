package com.example.mallmanagementapplication.repository;

import com.example.mallmanagementapplication.model.StaffAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffAssignmentRepository extends JpaRepository<StaffAssignment, Long> {
    List<StaffAssignment> findByStaffId(Long staffId);

}
