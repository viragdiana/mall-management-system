package com.example.mallmanagementapplication.service;

import com.example.mallmanagementapplication.model.Floor;
import com.example.mallmanagementapplication.model.MaintenanceStaff;
import com.example.mallmanagementapplication.model.StaffAssignment;
import com.example.mallmanagementapplication.repository.FloorRepository;
import com.example.mallmanagementapplication.repository.MaintenanceStaffRepository;
import com.example.mallmanagementapplication.repository.StaffAssignmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffAssignmentService {

    private final StaffAssignmentRepository repo;
    private final FloorRepository floorRepo;
    private final MaintenanceStaffRepository staffRepo;

    public StaffAssignmentService(
            StaffAssignmentRepository repo,
            FloorRepository floorRepo,
            MaintenanceStaffRepository staffRepo
    ) {
        this.repo = repo;
        this.floorRepo = floorRepo;
        this.staffRepo = staffRepo;
    }

    // -------- GET ALL --------
    public List<StaffAssignment> getAll() {
        return repo.findAll();
    }

    // -------- GET BY ID --------
    public StaffAssignment getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Assignment not found: " + id));
    }

    // -------- SAVE WITH VALIDATION --------
    public StaffAssignment save(StaffAssignment assignment) {

        if (assignment.getShift() == null) {
            throw new IllegalStateException("Shift cannot be null!");
        }

        // VALIDATE FLOOR
        if (assignment.getFloor() == null || assignment.getFloor().getId() == null) {
            throw new IllegalStateException("Assignment must reference a valid floor!");
        }

        Floor floor = floorRepo.findById(assignment.getFloor().getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Floor not found: " + assignment.getFloor().getId()
                ));

        // VALIDATE STAFF
        if (assignment.getStaff() == null || assignment.getStaff().getId() == null) {
            throw new IllegalStateException("Assignment must reference valid maintenance staff!");
        }

        MaintenanceStaff staff = staffRepo.findById(assignment.getStaff().getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Maintenance staff not found: " + assignment.getStaff().getId()
                ));

        assignment.setFloor(floor);
        assignment.setStaff(staff);

        return repo.save(assignment);
    }

    // -------- DELETE --------
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Assignment not found: " + id);
        }
        repo.deleteById(id);
    }
}
