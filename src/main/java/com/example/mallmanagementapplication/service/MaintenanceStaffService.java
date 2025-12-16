package com.example.mallmanagementapplication.service;

import com.example.mallmanagementapplication.model.MaintenanceStaff;
import com.example.mallmanagementapplication.repository.MaintenanceStaffRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaintenanceStaffService {

    private final MaintenanceStaffRepository repo;

    public MaintenanceStaffService(MaintenanceStaffRepository repo) {
        this.repo = repo;
    }

    public List<MaintenanceStaff> getAll() {
        return repo.findAll();
    }

    public MaintenanceStaff getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Staff not found: " + id));
    }

    public MaintenanceStaff save(MaintenanceStaff staff) {
        return repo.save(staff);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Staff not found: " + id);
        }
        repo.deleteById(id);
    }
}