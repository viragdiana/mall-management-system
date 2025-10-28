package com.example.mallmanagementapplication.controller;

import com.example.mallmanagementapplication.model.Purchase;
import com.example.mallmanagementapplication.service.PurchaseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping
    public void addPurchase(@RequestBody Purchase purchase) {
        purchaseService.addPurchase(purchase);
    }

    @GetMapping
    public List<Purchase> getAllPurchases() {
        return purchaseService.getAllPurchases();
    }

    @GetMapping("/{id}")
    public Optional<Purchase> getPurchaseById(@PathVariable String id) {
        return purchaseService.getPurchaseById(id);
    }

    @DeleteMapping("/{id}")
    public void deletePurchase(@PathVariable String id) {
        purchaseService.deletePurchase(id);
    }
}
