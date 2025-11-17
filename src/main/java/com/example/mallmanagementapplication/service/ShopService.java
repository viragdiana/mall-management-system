package com.example.mallmanagementapplication.service;

import com.example.mallmanagementapplication.model.Shop;
import com.example.mallmanagementapplication.repository.ShopRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.mallmanagementapplication.service.Validation.requireExists;

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
        return requireExists(shopRepo, id, "Shop");
    }

    public List<Shop> getAllShops() {
        return shopRepo.findAll();
    }

    public void deleteShop(String id) {
        requireExists(shopRepo, id, "Shop");
        shopRepo.delete(id);
    }
}
