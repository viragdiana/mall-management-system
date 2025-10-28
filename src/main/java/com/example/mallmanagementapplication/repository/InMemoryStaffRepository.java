package com.example.mallmanagementapplication.repository;

import com.example.mallmanagementapplication.model.Staff;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryStaffRepository
        extends InMemoryCrudRepository<Staff>
        implements StaffRepository {

    @Override
    protected String getId(Staff entity) {
        return entity.getId();
    }
}
