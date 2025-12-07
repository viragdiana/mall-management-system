package com.example.mallmanagementapplication.service;

import com.example.mallmanagementapplication.model.Customer;
import com.example.mallmanagementapplication.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repo;

    public CustomerService(CustomerRepository repo) {
        this.repo = repo;
    }

    public List<Customer> getAll() {
        return repo.findAll();
    }

    public Customer getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found: " + id));
    }

    public Customer save(Customer customer) {
        // business rule: email trebuie să fie unic (dar permite update pe același client)
        repo.findByEmail(customer.getEmail()).ifPresent(existing -> {
            if (customer.getId() == null || !existing.getId().equals(customer.getId())) {
                throw new IllegalStateException("Email already exists!");
            }
        });

        return repo.save(customer);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Customer not found: " + id);
        }
        repo.deleteById(id);
    }
}
