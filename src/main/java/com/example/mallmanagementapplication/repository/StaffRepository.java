package com.example.mallmanagementapplication.repository;

import com.example.mallmanagementapplication.model.Staff;
import com.example.mallmanagementapplication.model.SecurityStaff;
import com.example.mallmanagementapplication.model.MaintenanceStaff;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StaffRepository extends InMemoryRepository<Staff> {
    public List<SecurityStaff> findAllSecurity() {
        return store.values().stream()
                .filter(s -> s instanceof SecurityStaff)
                .map(s -> (SecurityStaff) s)
                .collect(Collectors.toList());
    }

    public List<MaintenanceStaff> findAllMaintenance() {
        return store.values().stream()
                .filter(s -> s instanceof MaintenanceStaff)
                .map(s -> (MaintenanceStaff) s)
                .collect(Collectors.toList());
    }
}

