package com.example.mallmanagementapplication.service;

import com.example.mallmanagementapplication.model.SecurityStaff;
import com.example.mallmanagementapplication.repository.SecurityStaffRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityStaffService {

    private final SecurityStaffRepository repo;

    public SecurityStaffService(SecurityStaffRepository repo) {
        this.repo = repo;
    }

    public List<SecurityStaff> getAll() {
        return repo.findAll();
    }

    public SecurityStaff getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Security staff not found: " + id));
    }

    public SecurityStaff save(SecurityStaff staff) {
        return repo.save(staff);
    }

    public void delete(Long id) {
        if (!repo.existsById(id))
            throw new EntityNotFoundException("Security staff not found: " + id);

        repo.deleteById(id);
    }
}
