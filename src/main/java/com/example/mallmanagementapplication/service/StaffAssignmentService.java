package com.example.mallmanagementapplication.service;

import com.example.mallmanagementapplication.model.*;
import com.example.mallmanagementapplication.repository.FloorRepository;
import com.example.mallmanagementapplication.repository.StaffAssignmentRepository;
import com.example.mallmanagementapplication.repository.StaffRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.mallmanagementapplication.service.Validation.requireExists;
@Service
public class StaffAssignmentService {
    private final StaffAssignmentRepository assignmentRepo;
    private final FloorRepository floorRepo;
    private final StaffRepository staffRepo;

    public StaffAssignmentService(StaffAssignmentRepository assignmentRepo,
                                  FloorRepository floorRepo,
                                  StaffRepository staffRepo) {
        this.assignmentRepo = assignmentRepo;
        this.floorRepo = floorRepo;
        this.staffRepo = staffRepo;
    }

    public void createAssignment(StaffAssignment assignment) {
        // 1) floor valid
        Floor floor = requireExists(floorRepo, assignment.getFloorId(), "Floor");

        // 2) staff valid
        Staff staff = requireExists(staffRepo, assignment.getStaffId(), "Staff");

        // 3) salvăm
        assignmentRepo.save(assignment);

        // 4) sincronizăm pe Floor.assignments
        if (floor.getAssignments().stream().noneMatch(a -> a.getId().equals(assignment.getId()))) {
            floor.addAssignment(assignment);
        }

        // 5) dacă staff-ul e MaintenanceStaff, sincronizăm și acolo
        if (staff instanceof MaintenanceStaff) {
            MaintenanceStaff ms = (MaintenanceStaff) staff;
            if (ms.getAssignments().stream().noneMatch(a -> a.getId().equals(assignment.getId()))) {
                ms.addAssignment(assignment);
            }
        }
    }

    public StaffAssignment getAssignment(String id) {
        return assignmentRepo.findById(id);
    }

    public List<StaffAssignment> getAllAssignments() {
        return assignmentRepo.findAll();
    }

    public void deleteAssignment(String id) {
        assignmentRepo.delete(id);
        // opțional: șterge și din Floor/Staff listele aferente
    }

    public List<StaffAssignment> getAssignmentsByShift(Shift shift) {
       return assignmentRepo.findByShift(shift);
    }
}
