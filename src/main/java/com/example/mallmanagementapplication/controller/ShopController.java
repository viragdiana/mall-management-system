package com.example.mallmanagementapplication.controller;

import com.example.mallmanagementapplication.model.Shop;
import com.example.mallmanagementapplication.service.ShopService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/shops")
public class ShopController {

    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @PostMapping
    public void addShop(@RequestBody Shop shop) {
        shopService.addShop(shop);
    }

    @GetMapping
    public List<Shop> getAllShops() {
        return shopService.getAllShops();
    }

    @GetMapping("/{id}")
    public Optional<Shop> getShopById(@PathVariable String id) {
        return shopService.getShopById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteShop(@PathVariable String id) {
        shopService.deleteShop(id);
    }
}
