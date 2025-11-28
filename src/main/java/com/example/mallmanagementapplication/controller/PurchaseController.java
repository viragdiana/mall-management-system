package com.example.mallmanagementapplication.controller;

import com.example.mallmanagementapplication.model.Purchase;
import com.example.mallmanagementapplication.service.PurchaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/purchases")
public class PurchaseController {

    private final PurchaseService service;

    public PurchaseController(PurchaseService service) {
        this.service = service;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("purchases", service.getAll());
        return "purchases/index";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("purchase", service.getById(id));
        return "purchases/details";
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("purchase", new Purchase());
        return "purchases/form";
    }

    @PostMapping
    public String create(@ModelAttribute Purchase purchase) {
        service.save(purchase);
        return "redirect:/purchases";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("purchase", service.getById(id));
        return "purchases/edit";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute Purchase updated) {
        Purchase existing = service.getById(id);

        existing.setAmount(updated.getAmount());
        existing.setCustomer(updated.getCustomer());
        existing.setShop(updated.getShop());

        service.save(existing);
        return "redirect:/purchases";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/purchases";
    }
}
