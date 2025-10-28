package com.example.mallmanagementapplication.repository;

import com.example.mallmanagementapplication.model.Customer;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryCustomerRepository
        extends InMemoryCrudRepository<Customer>
        implements CustomerRepository {

    @Override
    protected String getId(Customer entity) {
        return entity.getId();
    }
}

