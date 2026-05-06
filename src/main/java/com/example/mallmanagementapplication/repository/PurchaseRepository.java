package com.example.mallmanagementapplication.repository;

import com.example.mallmanagementapplication.model.Purchase;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    // filter by customer + sort
    List<Purchase> findByCustomer_Id(Long customerId, Sort sort);
}