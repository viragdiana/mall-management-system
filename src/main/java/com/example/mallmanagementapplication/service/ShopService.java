package com.example.mallmanagementapplication.service;

import com.example.mallmanagementapplication.model.Shop;
import com.example.mallmanagementapplication.repository.ShopRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopService {
    private final ShopRepository shopRepository;

    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public void addShop(Shop shop) {
        shopRepository.save(shop);
    }

    public List<Shop> getAllShops() {
        return shopRepository.findAll();
    }

    public Optional<Shop> getShopById(String id) {
        return shopRepository.findById(id);
    }

    public void deleteShop(String id) {
        shopRepository.delete(id);
    }
}
