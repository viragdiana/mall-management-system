package com.example.mallmanagementapplication.controller;

import com.example.mallmanagementapplication.model.Shop;
import com.example.mallmanagementapplication.service.ShopService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shops")
public class ShopController {

    private final ShopService service;

    public ShopController(ShopService service) {
        this.service = service;
    }

    @GetMapping
    public List<Shop> getAllShops() {
        return service.getAllShops();
    }

    @GetMapping("/{id}")
    public Shop getShop(@PathVariable String id) {
        return service.getShop(id);
    }

    @PostMapping
    public void addShop(@RequestBody Shop shop) {
        service.addShop(shop);
    }

    @DeleteMapping("/{id}")
    public void deleteShop(@PathVariable String id) {
        service.deleteShop(id);
    }
}

