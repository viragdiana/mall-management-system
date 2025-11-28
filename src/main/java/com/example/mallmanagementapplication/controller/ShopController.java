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

    @GetMapping
    public String index(Model model) {
        model.addAttribute("shops", service.getAll());
        return "shops/index";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("shop", service.getById(id));
        return "shops/details";
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("shop", new Shop());
        model.addAttribute("types", ShopType.values());
        return "shops/form";
    }

    @PostMapping
    public String create(@ModelAttribute Shop shop) {
        service.save(shop);
        return "redirect:/shops";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("shop", service.getById(id));
        model.addAttribute("types", ShopType.values());
        return "shops/edit";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute Shop updated) {
        Shop existing = service.getById(id);

        existing.setName(updated.getName());
        existing.setOwnerName(updated.getOwnerName());
        existing.setAreaSqm(updated.getAreaSqm());
        existing.setType(updated.getType());

        service.save(existing);
        return "redirect:/shops";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/shops";
    }
}
