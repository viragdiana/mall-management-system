package com.example.mallmanagementapplication.service;

import com.example.mallmanagementapplication.model.SecurityStaff;
import com.example.mallmanagementapplication.repository.SecurityStaffRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.mallmanagementapplication.service.Validation.requireExists;

@Service
public class SecurityStaffService {

    private final SecurityStaffRepository repo;

    public SecurityStaffService(SecurityStaffRepository repo) {
        this.repo = repo;
    }

    public void add(SecurityStaff staff) {
        repo.save(staff);
    }

    public List<SecurityStaff> getAll() {
        return repo.findAll();
    }

    public SecurityStaff get(String id) {
        return requireExists(repo, id, "SecurityStaff");
    }

    public void delete(String id) {
        requireExists(repo, id, "SecurityStaff");
        repo.delete(id);
    }
}
