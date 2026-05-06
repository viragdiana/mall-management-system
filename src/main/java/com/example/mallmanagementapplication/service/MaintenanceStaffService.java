package com.example.mallmanagementapplication.service;

import com.example.mallmanagementapplication.model.MaintenanceStaff;
import com.example.mallmanagementapplication.repository.MaintenanceStaffRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaintenanceStaffService {

    private final MaintenanceStaffRepository repo;

    public MaintenanceStaffService(MaintenanceStaffRepository repo) {
        this.repo = repo;
    }

    /* ========== LIST ALL (used elsewhere) ========== */
    public List<MaintenanceStaff> getAll() {
        return repo.findAll();
    }

    /* ========== FILTER BY NAME + SORT ========== */
    public List<MaintenanceStaff> getFilteredAndSorted(
            String name,
            Sort sort
    ) {
        if (name == null || name.isBlank()) {
            return repo.findAll(sort);
        }
        return repo.findByNameContainingIgnoreCase(name.trim(), sort);
    }

    /* ========== GET BY ID ========== */
    public MaintenanceStaff getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Staff not found: " + id));
    }

    /* ========== SAVE ========== */
    public MaintenanceStaff save(MaintenanceStaff staff) {
        return repo.save(staff);
    }

    /* ========== DELETE ========== */
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Staff not found: " + id);
        }
        repo.deleteById(id);
    }
}