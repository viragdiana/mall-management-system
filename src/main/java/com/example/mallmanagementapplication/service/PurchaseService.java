package com.example.mallmanagementapplication.service;

import com.example.mallmanagementapplication.model.Customer;
import com.example.mallmanagementapplication.model.Purchase;
import com.example.mallmanagementapplication.model.Shop;
import com.example.mallmanagementapplication.repository.CustomerRepository;
import com.example.mallmanagementapplication.repository.PurchaseRepository;
import com.example.mallmanagementapplication.repository.ShopRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PurchaseService {

    private final PurchaseRepository repo;
    private final CustomerRepository customerRepo;
    private final ShopRepository shopRepo;

    public PurchaseService(PurchaseRepository repo,
                           CustomerRepository customerRepo,
                           ShopRepository shopRepo) {
        this.repo = repo;
        this.customerRepo = customerRepo;
        this.shopRepo = shopRepo;
    }

    public List<Purchase> getAll() {
        return repo.findAll();
    }

    public Purchase getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Purchase not found: " + id));
    }

    public Purchase save(Purchase purchase) {

        if (purchase.getAmount() == null ||
                purchase.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalStateException("Amount must be positive!");
        }

        // Validate CUSTOMER exists
        if (purchase.getCustomer() == null || purchase.getCustomer().getId() == null) {
            throw new IllegalStateException("Purchase must have a customer!");
        }

        Customer customer = customerRepo.findById(purchase.getCustomer().getId())
                .orElseThrow(() -> new IllegalStateException("Customer does not exist!"));

        purchase.setCustomer(customer);

        // Validate SHOP exists
        if (purchase.getShop() == null || purchase.getShop().getId() == null) {
            throw new IllegalStateException("Purchase must have a shop!");
        }

        Shop shop = shopRepo.findById(purchase.getShop().getId())
                .orElseThrow(() -> new IllegalStateException("Shop does not exist!"));

        purchase.setShop(shop);

        return repo.save(purchase);
    }

    public void delete(Long id) {
        Purchase p = getById(id);
        repo.delete(p);
    }
}
