package com.example.mallmanagementapplication.repository;

import com.example.mallmanagementapplication.model.Staff;

import java.util.*;
public class InMemoryStaffRepository implements StaffRepository {
    private final Map<String, Staff> staffMembers = new HashMap<>();

    @Override
    public void save(Staff staff) {
        staffMembers.put(staff.getId(), staff);
    }

    @Override
    public List<Staff> findAll() {
        return new ArrayList<>(staffMembers.values());
    }

    @Override
    public Optional<Staff> findById(String id) {
        return Optional.ofNullable(staffMembers.get(id));
    }

    @Override
    public void delete(String id) {
        staffMembers.remove(id);
    }
}
