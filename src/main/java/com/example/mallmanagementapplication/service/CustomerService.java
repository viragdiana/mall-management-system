package com.example.mallmanagementapplication.service;

import com.example.mallmanagementapplication.model.Customer;
import com.example.mallmanagementapplication.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repo;

    public CustomerService(CustomerRepository repo) {
        this.repo = repo;
    }

    /* ========== GET ALL (folosit de alte controllere / dropdown-uri) ========== */
    public List<Customer> getAll() {
        return repo.findAll();
    }

    /* ========== FILTER + SORT ========== */
    public List<Customer> getFilteredAndSorted(
            String name,
            String currency,
            String email,
            Sort sort
    ) {
        return repo.findByNameContainingIgnoreCaseAndCurrencyContainingIgnoreCaseAndEmailContainingIgnoreCase(
                name == null ? "" : name.trim(),
                currency == null ? "" : currency.trim(),
                email == null ? "" : email.trim(),
                sort
        );
    }

    /* ========== GET BY ID ========== */
    public Customer getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found: " + id));
    }

    /* ========== SAVE ========== */
    public Customer save(Customer customer) {
        repo.findByEmailIgnoreCase(customer.getEmail())
                .ifPresent(existing -> {
                    if (customer.getId() == null || !existing.getId().equals(customer.getId())) {
                        throw new IllegalStateException("Email already exists!");
                    }
                });
        return repo.save(customer);
    }

    /* ========== DELETE ========== */
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Customer not found: " + id);
        }
        repo.deleteById(id);
    }
}