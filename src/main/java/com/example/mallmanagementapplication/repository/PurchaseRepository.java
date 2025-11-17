package com.example.mallmanagementapplication.repository;

import com.example.mallmanagementapplication.model.Purchase;
import org.springframework.stereotype.Repository;

@Repository
public class PurchaseRepository extends InFileRepository<Purchase> {

    public PurchaseRepository() {
        super("src/main/resources/data/purchases.json", Purchase.class);
    }
}
