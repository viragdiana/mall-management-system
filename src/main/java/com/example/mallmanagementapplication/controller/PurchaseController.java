package com.example.mallmanagementapplication.controller;

import com.example.mallmanagementapplication.model.Purchase;
import com.example.mallmanagementapplication.service.PurchaseService;
import com.example.mallmanagementapplication.service.CustomerService;
import com.example.mallmanagementapplication.service.ShopService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/purchases")
public class PurchaseController {

    private final PurchaseService service;
    private final CustomerService customerService;
    private final ShopService shopService;

    public PurchaseController(PurchaseService service,
                              CustomerService customerService,
                              ShopService shopService) {
        this.service = service;
        this.customerService = customerService;
        this.shopService = shopService;
    }

    // GET /purchases → show all purchases
    @GetMapping
    public String showAllPurchases(Model model) {
        model.addAttribute("purchases", service.getAllPurchases());
        return "purchases/index"; // templates/purchases/index.html
    }

    // GET /purchases/new → show form for new purchase
    @GetMapping("/new")
    public String showPurchaseForm(Model model) {
        model.addAttribute("purchase", new Purchase());
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("shops", shopService.getAllShops());
        return "purchases/form";
    }


    // POST /purchases → create new purchase
    @PostMapping
    public String addPurchase(@ModelAttribute Purchase purchase) {
        service.createPurchase(purchase);
        return "redirect:/purchases";
    }

    // POST /purchases/{id}/delete → delete purchase
    @PostMapping("/{id}/delete")
    public String deletePurchase(@PathVariable String id) {
        service.deletePurchase(id);
        return "redirect:/purchases";
    }
}
