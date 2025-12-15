package com.example.mallmanagementapplication.service;

import com.example.mallmanagementapplication.model.Floor;
import com.example.mallmanagementapplication.model.MaintenanceTask;
import com.example.mallmanagementapplication.model.StaffAssignment;
import com.example.mallmanagementapplication.repository.FloorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FloorService {

    private final FloorRepository repo;

    public FloorService(FloorRepository repo) {
        this.repo = repo;
    }

    public List<Floor> getAll() {
        return repo.findAll();
    }

    public Floor getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Floor not found: " + id));
    }

    public Floor save(Floor floor) {

        if (floor.getMall() == null || floor.getMall().getId() == null) {
            throw new IllegalStateException("Floor must belong to a mall.");
        }

        repo.findByLevelAndMall_Id(floor.getLevel(), floor.getMall().getId())
                .ifPresent(existing -> {
                    if (floor.getId() == null || !existing.getId().equals(floor.getId())) {
                        throw new IllegalStateException(
                                "A floor with this level already exists in this mall!"
                        );
                    }
                });

        return repo.save(floor);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Floor not found: " + id);
        }
        repo.deleteById(id);
    }

    /**
     * 🔥 TASK-URI DERIVATE (Floor → Assignments → Tasks)
     */
    public List<MaintenanceTask> getTasksForFloor(Floor floor) {
        List<MaintenanceTask> tasks = new ArrayList<>();

        for (StaffAssignment a : floor.getAssignments()) {
            tasks.addAll(a.getTasks());
        }

        return tasks;
    }
}
