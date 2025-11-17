package com.example.mallmanagementapplication.service;

import com.example.mallmanagementapplication.model.Purchase;
import com.example.mallmanagementapplication.model.Customer;
import com.example.mallmanagementapplication.model.Shop;
import com.example.mallmanagementapplication.repository.CustomerRepository;
import com.example.mallmanagementapplication.repository.ShopRepository;
import com.example.mallmanagementapplication.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.mallmanagementapplication.service.Validation.requireExists;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepo;
    private final CustomerRepository customerRepo;
    private final ShopRepository shopRepo;

    public PurchaseService(PurchaseRepository purchaseRepo,
                           CustomerRepository customerRepo,
                           ShopRepository shopRepo) {
        this.purchaseRepo = purchaseRepo;
        this.customerRepo = customerRepo;
        this.shopRepo = shopRepo;
    }

    public void createPurchase(Purchase purchase) {
        requireExists(customerRepo, purchase.getCustomerId(), "Customer");
        requireExists(shopRepo, purchase.getShopId(), "Shop");

        purchaseRepo.save(purchase);
    }

    public Purchase getPurchase(String id) {
        return requireExists(purchaseRepo, id, "Purchase");
    }

    public List<Purchase> getAllPurchases() {
        return purchaseRepo.findAll();
    }

    public void deletePurchase(String id) {
        requireExists(purchaseRepo, id, "Purchase");
        purchaseRepo.delete(id);
    }
}
