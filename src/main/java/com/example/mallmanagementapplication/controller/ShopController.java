package com.example.mallmanagementapplication.controller;

import com.example.mallmanagementapplication.model.Shop;
import com.example.mallmanagementapplication.model.ShopType;
import com.example.mallmanagementapplication.service.ShopService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;
@Controller
@RequestMapping("/shops")
public class ShopController {

    private final ShopService service;

    public ShopController(ShopService service) {
        this.service = service;
    }

    // ✅ GET ALL
    @GetMapping
    public String getAllShops(Model model) {
        model.addAttribute("shops", service.getAllShops());
        return "shops/index";
    }

    // ✅ CREATE FORM
    @GetMapping("/new")
    public String showShopForm(Model model) {
        model.addAttribute("shop", new Shop());
        model.addAttribute("types", ShopType.values()); // dropdown options
        return "shops/form";
    }

    // ✅ CREATE (POST)
    @PostMapping
    public String addShop(@ModelAttribute Shop shop) {
        service.addShop(shop);
        return "redirect:/shops";
    }

    // ✅ DELETE (POST)
    @PostMapping("/{id}/delete")
    public String deleteShop(@PathVariable String id) {
        service.deleteShop(id);
        return "redirect:/shops";
    }
}