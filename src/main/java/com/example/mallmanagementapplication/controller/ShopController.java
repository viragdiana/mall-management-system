package com.example.mallmanagementapplication.controller;

import com.example.mallmanagementapplication.model.Shop;
import com.example.mallmanagementapplication.model.ShopType;
import com.example.mallmanagementapplication.service.FloorService;
import com.example.mallmanagementapplication.service.ShopService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/shops")
public class ShopController {

    private final ShopService service;
    private final FloorService floorService;

    public ShopController(ShopService service, FloorService floorService) {
        this.service = service;
        this.floorService = floorService;
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
    public String newForm(Model model) {
        model.addAttribute("shop", new Shop());
        model.addAttribute("floors", floorService.getAll());
        model.addAttribute("types", ShopType.values());
        return "shops/new";
    }

    @PostMapping
    public String create(
            @Valid @ModelAttribute("shop") Shop shop,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("floors", floorService.getAll());
            model.addAttribute("types", ShopType.values());
            return "shops/new";
        }

        try {
            service.save(shop);
        } catch (IllegalStateException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("floors", floorService.getAll());
            model.addAttribute("types", ShopType.values());
            return "shops/new";
        }

        return "redirect:/shops";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("shop", service.getById(id));
        model.addAttribute("floors", floorService.getAll());
        model.addAttribute("types", ShopType.values());
        return "shops/edit";
    }

    @PostMapping("/{id}/edit")
    public String update(
            @PathVariable Long id,
            @Valid @ModelAttribute("shop") Shop updated,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("floors", floorService.getAll());
            model.addAttribute("types", ShopType.values());
            return "shops/edit";
        }

        Shop existing = service.getById(id);
        existing.setName(updated.getName());
        existing.setOwnerName(updated.getOwnerName());
        existing.setAreaSqm(updated.getAreaSqm());
        existing.setType(updated.getType());
        existing.setFloor(updated.getFloor());

        try {
            service.save(existing);
        } catch (IllegalStateException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("floors", floorService.getAll());
            model.addAttribute("types", ShopType.values());
            return "shops/edit";
        }

        return "redirect:/shops";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/shops";
    }
}
