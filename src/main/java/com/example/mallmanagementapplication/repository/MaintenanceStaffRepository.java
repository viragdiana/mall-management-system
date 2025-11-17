package com.example.mallmanagementapplication.repository;

import com.example.mallmanagementapplication.model.MaintenanceStaff;
import org.springframework.stereotype.Repository;

@Repository
public class MaintenanceStaffRepository extends InFileRepository<MaintenanceStaff> {

    public MaintenanceStaffRepository() {
        super("src/main/resources/data/maintenance_staff.json", MaintenanceStaff.class);
    }
}
