package com.example.mallmanagementapplication.service;

import com.example.mallmanagementapplication.model.Staff;
import com.example.mallmanagementapplication.repository.StaffRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StaffService {
    private final StaffRepository staffRepo;

    public StaffService(StaffRepository staffRepo) {
        this.staffRepo = staffRepo;
    }

    public void addStaff(Staff staff) {
        staffRepo.save(staff);
    }

    public Staff getStaff(String id) {
        return staffRepo.findById(id);
    }

    public List<Staff> getAllStaff() {
        return staffRepo.findAll();
    }

    public void deleteStaff(String id) {
        staffRepo.delete(id);
    }

}
