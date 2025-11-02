package com.example.mallmanagementapplication.controller;

import com.example.mallmanagementapplication.model.Purchase;
import com.example.mallmanagementapplication.service.PurchaseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    private final PurchaseService service;

    public PurchaseController(PurchaseService service) {
        this.service = service;
    }

    @GetMapping
    public List<Purchase> getAllPurchases() {
        return service.getAllPurchases();
    }

    @GetMapping("/{id}")
    public Purchase getPurchase(@PathVariable String id) {
        return service.getPurchase(id);
    }

    @PostMapping
    public String createPurchase(@RequestBody Purchase purchase) {
        service.createPurchase(purchase);
        return "Purchase created successfully.";
    }

    @DeleteMapping("/{id}")
    public void deletePurchase(@PathVariable String id) {
        service.deletePurchase(id);
    }
}
