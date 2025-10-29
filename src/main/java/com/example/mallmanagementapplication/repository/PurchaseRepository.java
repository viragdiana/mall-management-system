package com.example.mallmanagementapplication.repository;

import com.example.mallmanagementapplication.model.Purchase;
import java.util.List;
import java.util.stream.Collectors;

public class PurchaseRepository extends InMemoryRepository<Purchase> {
    public List<Purchase> findByCustomerId(String customerId) {
        return store.values().stream()
                .filter(p -> p.getCustomerId().equals(customerId))
                .collect(Collectors.toList());
    }

    public List<Purchase> findByShopId(String shopId) {
        return store.values().stream()
                .filter(p -> p.getShopId().equals(shopId))
                .collect(Collectors.toList());
    }
}
