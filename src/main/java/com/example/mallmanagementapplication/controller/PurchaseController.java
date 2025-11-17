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

    // LIST
    @GetMapping
    public String index(Model model) {
        model.addAttribute("purchases", service.getAllPurchases());
        return "purchases/index";
    }

    // DETAILS
    @GetMapping("/{id}")
    public String details(@PathVariable String id, Model model) {
        model.addAttribute("purchase", service.getPurchase(id));
        model.addAttribute("customer", customerService.getCustomer(service.getPurchase(id).getCustomerId()));
        model.addAttribute("shop", shopService.getShop(service.getPurchase(id).getShopId()));
        return "purchases/details";
    }

    // CREATE FORM
    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("purchase", new Purchase());
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("shops", shopService.getAllShops());
        return "purchases/form";
    }

    // CREATE
    @PostMapping
    public String create(@ModelAttribute Purchase purchase) {
        service.createPurchase(purchase);
        return "redirect:/purchases";
    }

    // EDIT FORM
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable String id, Model model) {
        Purchase existing = service.getPurchase(id);

        model.addAttribute("purchase", existing);
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("shops", shopService.getAllShops());

        return "purchases/edit";
    }

    // UPDATE
    @PostMapping("/{id}/edit")
    public String update(@PathVariable String id, @ModelAttribute Purchase updated) {
        Purchase existing = service.getPurchase(id);

        existing.setCustomerId(updated.getCustomerId());
        existing.setShopId(updated.getShopId());
        existing.setAmount(updated.getAmount());

        service.createPurchase(existing);
        return "redirect:/purchases";
    }

    // DELETE
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.deletePurchase(id);
        return "redirect:/purchases";
    }
}
