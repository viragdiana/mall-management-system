package com.example.mallmanagementapplication.repository;

import com.example.mallmanagementapplication.model.StaffAssignment;
import com.example.mallmanagementapplication.model.Shift;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
@Repository
public class StaffAssignmentRepository extends InMemoryRepository<StaffAssignment> {

    public List<StaffAssignment> findByShift(Shift shift) {
        return store.values().stream()
                .filter(a -> a.getShift() == shift)
                .collect(Collectors.toList());
    }
}