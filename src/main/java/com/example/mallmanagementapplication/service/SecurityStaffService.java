package com.example.mallmanagementapplication.service;

import com.example.mallmanagementapplication.model.SecurityStaff;
import com.example.mallmanagementapplication.repository.SecurityStaffRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityStaffService {

    private final SecurityStaffRepository repo;

    public SecurityStaffService(SecurityStaffRepository repo) {
        this.repo = repo;
    }

    /* ========== LIST ALL (used elsewhere) ========== */
    public List<SecurityStaff> getAll() {
        return repo.findAll();
    }

    /* ========== FILTER BY NAME + SORT ========== */
    public List<SecurityStaff> getFilteredAndSorted(
            String name,
            Sort sort
    ) {
        if (name == null || name.isBlank()) {
            return repo.findAll(sort);
        }
        return repo.findByNameContainingIgnoreCase(name.trim(), sort);
    }

    /* ========== GET BY ID ========== */
    public SecurityStaff getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Security staff not found: " + id));
    }

    /* ========== SAVE ========== */
    public SecurityStaff save(SecurityStaff staff) {
        return repo.save(staff);
    }

    /* ========== DELETE ========== */
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Security staff not found: " + id);
        }
        repo.deleteById(id);
    }
}