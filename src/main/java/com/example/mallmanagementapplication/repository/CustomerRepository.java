package com.example.mallmanagementapplication.repository;

import com.example.mallmanagementapplication.model.Customer;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerRepository extends InMemoryRepository<Customer> {
    public List<Customer> findByName(String name) {
        return store.values().stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }
}
