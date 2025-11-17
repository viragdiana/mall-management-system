package com.example.mallmanagementapplication.repository;

import com.example.mallmanagementapplication.model.SecurityStaff;
import org.springframework.stereotype.Repository;

@Repository
public class SecurityStaffRepository extends InFileRepository<SecurityStaff> {

    public SecurityStaffRepository() {
        super("src/main/resources/data/security_staff.json", SecurityStaff.class);
    }
}
