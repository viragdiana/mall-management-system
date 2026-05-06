package com.example.mallmanagementapplication.repository;

import com.example.mallmanagementapplication.model.SecurityStaff;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecurityStaffRepository extends JpaRepository<SecurityStaff, Long> {

    // filter by name + sort
    List<SecurityStaff> findByNameContainingIgnoreCase(
            String name,
            Sort sort
    );
}