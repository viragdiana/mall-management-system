package com.example.mallmanagementapplication.repository;

import com.example.mallmanagementapplication.model.Customer;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmailIgnoreCase(String email);

    List<Customer> findByNameContainingIgnoreCaseAndCurrencyContainingIgnoreCaseAndEmailContainingIgnoreCase(
            String name,
            String currency,
            String email,
            Sort sort
    );
}