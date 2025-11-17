package com.example.mallmanagementapplication.repository;

import com.example.mallmanagementapplication.model.Customer;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepository extends InFileRepository<Customer> {

    public CustomerRepository() {
        super("src/main/resources/data/customers.json", Customer.class);
    }
}
