package com.example.mallmanagementapplication.service;

import com.example.mallmanagementapplication.model.Staff;
import com.example.mallmanagementapplication.repository.StaffRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffService {
    private final StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public void addStaff(Staff staff) {
        staffRepository.save(staff);
    }

    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    public Optional<Staff> getStaffById(String id) {
        return staffRepository.findById(id);
    }

    public void deleteStaff(String id) {
        staffRepository.delete(id);
    }
}
