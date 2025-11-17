package com.example.mallmanagementapplication.service;

import com.example.mallmanagementapplication.model.MaintenanceStaff;
import com.example.mallmanagementapplication.repository.MaintenanceStaffRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.mallmanagementapplication.service.Validation.requireExists;

@Service
public class MaintenanceStaffService {

    private final MaintenanceStaffRepository repo;

    public MaintenanceStaffService(MaintenanceStaffRepository repo) {
        this.repo = repo;
    }

    public void add(MaintenanceStaff staff) {
        repo.save(staff);
    }

    public List<MaintenanceStaff> getAll() {
        return repo.findAll();
    }

    public MaintenanceStaff get(String id) {
        return requireExists(repo, id, "MaintenanceStaff");
    }

    public void delete(String id) {
        requireExists(repo, id, "MaintenanceStaff");
        repo.delete(id);
    }
}
