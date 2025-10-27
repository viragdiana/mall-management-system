package com.example.mallmanagementapplication.repository;
import com.example.mallmanagementapplication.model.Shop;

import java.util.*;

public class InMemoryShopRepository implements ShopRepository{
    private final Map<String, Shop> shops = new HashMap<>();

    @Override
    public void save(Shop shop) {
        shops.put(shop.getId(), shop);
    }

    @Override
    public List<Shop> findAll() {
        return new ArrayList<>(shops.values());
    }

    @Override
    public Optional<Shop> findById(String id) {
        return Optional.ofNullable(shops.get(id));
    }

    @Override
    public void delete(String id) {
        shops.remove(id);
    }
}
