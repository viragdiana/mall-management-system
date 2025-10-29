package com.example.mallmanagementapplication.repository;

import com.example.mallmanagementapplication.model.Shop;
import java.util.List;
import java.util.stream.Collectors;

public class ShopRepository extends InMemoryRepository<Shop> {
    public List<Shop> findByOwner(String ownerName) {
        return store.values().stream()
                .filter(s -> s.getOwnerName().equalsIgnoreCase(ownerName))
                .collect(Collectors.toList());
    }
}
