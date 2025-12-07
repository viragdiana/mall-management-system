package com.example.mallmanagementapplication.service;

import com.example.mallmanagementapplication.model.StaffAssignment;
import com.example.mallmanagementapplication.repository.StaffAssignmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffAssignmentService {

    private final StaffAssignmentRepository repo;

    public StaffAssignmentService(StaffAssignmentRepository repo) {
        this.repo = repo;
    }

    public List<StaffAssignment> getAll() {
        return repo.findAll();
    }

    public StaffAssignment getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Assignment not found: " + id));
    }

    public StaffAssignment save(StaffAssignment assignment) {
        return repo.save(assignment);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Assignment not found: " + id);
        }
        repo.deleteById(id);
    }
}
