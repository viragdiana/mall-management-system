package com.example.mallmanagementapplication.repository;

import com.example.mallmanagementapplication.model.SecurityStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityStaffRepository extends JpaRepository<SecurityStaff, Long> {
}
