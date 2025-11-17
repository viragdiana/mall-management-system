package com.example.mallmanagementapplication.service;

import com.example.mallmanagementapplication.model.*;
import com.example.mallmanagementapplication.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.mallmanagementapplication.service.Validation.requireExists;

@Service
public class StaffAssignmentService {

    private final StaffAssignmentRepository assignmentRepo;
    private final FloorRepository floorRepo;
    private final MaintenanceStaffRepository maintenanceRepo;
    private final SecurityStaffRepository securityRepo;

    public StaffAssignmentService(
            StaffAssignmentRepository assignmentRepo,
            FloorRepository floorRepo,
            MaintenanceStaffRepository maintenanceRepo,
            SecurityStaffRepository securityRepo) {
        this.assignmentRepo = assignmentRepo;
        this.floorRepo = floorRepo;
        this.maintenanceRepo = maintenanceRepo;
        this.securityRepo = securityRepo;
    }

    public void createAssignment(StaffAssignment assignment) {

        // Floor must exist
        requireExists(floorRepo, assignment.getFloorId(), "Floor");

        // STAFF MAY BE Maintenance or Security → check both
        Staff staff = maintenanceRepo.findById(assignment.getStaffId());
        if (staff == null) {
            staff = securityRepo.findById(assignment.getStaffId());
        }

        if (staff == null) {
            throw new IllegalArgumentException("No staff found with ID " + assignment.getStaffId());
        }

        assignmentRepo.save(assignment);
    }

    public List<StaffAssignment> getAllAssignments() {
        return assignmentRepo.findAll();
    }

    public StaffAssignment getAssignment(String id) {
        return requireExists(assignmentRepo, id, "Assignment");
    }

    public void deleteAssignment(String id) {
        requireExists(assignmentRepo, id, "Assignment");
        assignmentRepo.delete(id);
    }

    public List<StaffAssignment> getAssignmentsByShift(Shift shift) {
        return assignmentRepo.findByShift(shift);
    }
}
