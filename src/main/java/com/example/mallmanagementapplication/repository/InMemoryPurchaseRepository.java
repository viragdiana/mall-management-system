package com.example.mallmanagementapplication.repository;
import com.example.mallmanagementapplication.model.Purchase;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class InMemoryPurchaseRepository implements PurchaseRepository {
    private final Map<String, Purchase> purchases = new HashMap<>();

    @Override
    public void save(Purchase purchase) {
        purchases.put(purchase.getId(), purchase);
    }

    @Override
    public List<Purchase> findAll() {
        return new ArrayList<>(purchases.values());
    }

    @Override
    public Optional<Purchase> findById(String id) {
        return Optional.ofNullable(purchases.get(id));
    }

    @Override
    public void delete(String id) {
        purchases.remove(id);
    }
}
