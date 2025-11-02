package com.example.mallmanagementapplication.service;

import com.example.mallmanagementapplication.model.Shop;
import com.example.mallmanagementapplication.repository.ShopRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ShopService {
    private final ShopRepository shopRepo;

    public ShopService(ShopRepository shopRepo) {
        this.shopRepo = shopRepo;
    }

    public void addShop(Shop shop) {
        shopRepo.save(shop);
    }

    public Shop getShop(String id) {
        return shopRepo.findById(id);
    }

    public List<Shop> getAllShops() {
        return shopRepo.findAll();
    }

    public void deleteShop(String id) {
        shopRepo.delete(id);
    }
}
