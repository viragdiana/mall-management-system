package com.example.mallmanagementapplication.service;

import com.example.mallmanagementapplication.model.Customer;
import com.example.mallmanagementapplication.model.Purchase;
import com.example.mallmanagementapplication.model.Shop;
import com.example.mallmanagementapplication.repository.CustomerRepository;
import com.example.mallmanagementapplication.repository.PurchaseRepository;
import com.example.mallmanagementapplication.repository.ShopRepository;
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
        // 1) customerId valid
        Customer customer = requireExists(customerRepo, purchase.getCustomerId(), "Customer");

        // 2) shopId valid
        Shop shop = requireExists(shopRepo, purchase.getShopId(), "Shop");

        // 3) salvăm achiziția
        purchaseRepo.save(purchase);

        // 4) atașăm și în agregatele bidirecționale (listele din UML)
        if (customer.getPurchases().stream().noneMatch(p -> p.getId().equals(purchase.getId()))) {
            customer.addPurchase(purchase);
        }
        if (shop.getPurchases().stream().noneMatch(p -> p.getId().equals(purchase.getId()))) {
            shop.addPurchase(purchase);
        }
    }

    public Purchase getPurchase(String id) {
        return purchaseRepo.findById(id);
    }

    public List<Purchase> getAllPurchases() {
        return purchaseRepo.findAll();
    }

    public void deletePurchase(String id) {
        purchaseRepo.delete(id);
        // notă: poți, dacă vrei, să cauți în Customer/Shop și să elimini din listele lor după id
    }
}
