package com.example.mallmanagementapplication.repository;

import com.example.mallmanagementapplication.model.Shift;
import com.example.mallmanagementapplication.model.StaffAssignment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StaffAssignmentRepository extends InFileRepository<StaffAssignment> {

    public StaffAssignmentRepository() {
        super("src/main/resources/data/staff_assignments.json", StaffAssignment.class);
    }

    public List<StaffAssignment> findByShift(Shift shift) {
        return findAll().stream()
                .filter(a -> a.getShift() == shift)
                .collect(Collectors.toList());
    }
}
