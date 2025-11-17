package com.example.mallmanagementapplication.controller;

import com.example.mallmanagementapplication.model.Shop;
import com.example.mallmanagementapplication.model.ShopType;
import com.example.mallmanagementapplication.service.ShopService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/shops")
public class ShopController {

    private final ShopService service;

    public ShopController(ShopService service) {
        this.service = service;
    }

    // LIST ALL
    @GetMapping
    public String index(Model model) {
        model.addAttribute("shops", service.getAllShops());
        return "shops/index";
    }

    // DETAILS
    @GetMapping("/{id}")
    public String details(@PathVariable String id, Model model) {
        model.addAttribute("shop", service.getShop(id));
        return "shops/details";
    }

    // CREATE FORM
    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("shop", new Shop());
        model.addAttribute("types", ShopType.values());
        return "shops/form";
    }

    // CREATE
    @PostMapping
    public String create(@ModelAttribute Shop shop) {
        service.addShop(shop);
        return "redirect:/shops";
    }

    // EDIT FORM
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable String id, Model model) {
        model.addAttribute("shop", service.getShop(id));
        model.addAttribute("types", ShopType.values());
        return "shops/edit";
    }

    // UPDATE
    @PostMapping("/{id}/edit")
    public String update(@PathVariable String id, @ModelAttribute Shop updated) {
        Shop existing = service.getShop(id);

        existing.setName(updated.getName());
        existing.setOwnerName(updated.getOwnerName());
        existing.setAreaSqm(updated.getAreaSqm());
        existing.setType(updated.getType());

        service.addShop(existing);
        return "redirect:/shops";
    }

    // DELETE
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.deleteShop(id);
        return "redirect:/shops";
    }
}
