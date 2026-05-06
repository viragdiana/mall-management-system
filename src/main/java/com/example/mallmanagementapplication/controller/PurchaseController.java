package com.example.mallmanagementapplication.controller;

import com.example.mallmanagementapplication.model.Purchase;
import com.example.mallmanagementapplication.service.CustomerService;
import com.example.mallmanagementapplication.service.PurchaseService;
import com.example.mallmanagementapplication.service.ShopService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/purchases")
public class PurchaseController {

    private final PurchaseService service;
    private final CustomerService customerService;
    private final ShopService shopService;

    public PurchaseController(
            PurchaseService service,
            CustomerService customerService,
            ShopService shopService
    ) {
        this.service = service;
        this.customerService = customerService;
        this.shopService = shopService;
    }

    /* ===================== LIST + FILTER + SORT ===================== */
    @GetMapping
    public String index(
            @RequestParam(required = false) Long customerId,
            @RequestParam(defaultValue = "amount") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            Model model
    ) {
        if (!sortBy.equals("amount") && !sortBy.equals("customer")) {
            sortBy = "amount";
        }

        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy.equals("customer") ? "customer.name" : "amount").descending()
                : Sort.by(sortBy.equals("customer") ? "customer.name" : "amount").ascending();

        model.addAttribute(
                "purchases",
                service.getFilteredAndSorted(customerId, sort)
        );

        model.addAttribute("customers", customerService.getAll());
        model.addAttribute("customerId", customerId);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);

        return "purchases/index";
    }

    /* ===================== NEW ===================== */
    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("purchase", new Purchase());
        model.addAttribute("customers", customerService.getAll());
        model.addAttribute("shops", shopService.getAll());
        return "purchases/new";
    }

    /* ===================== DETAILS ===================== */
    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("purchase", service.getById(id));
        return "purchases/details";
    }

    /* ===================== CREATE ===================== */
    @PostMapping
    public String create(
            @Valid @ModelAttribute("purchase") Purchase purchase,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("customers", customerService.getAll());
            model.addAttribute("shops", shopService.getAll());
            return "purchases/new";
        }

        try {
            service.save(purchase);
        } catch (IllegalStateException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("customers", customerService.getAll());
            model.addAttribute("shops", shopService.getAll());
            return "purchases/new";
        }

        return "redirect:/purchases";
    }

    /* ===================== EDIT ===================== */
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("purchase", service.getById(id));
        model.addAttribute("customers", customerService.getAll());
        model.addAttribute("shops", shopService.getAll());
        return "purchases/edit";
    }

    /* ===================== UPDATE ===================== */
    @PostMapping("/{id}/edit")
    public String update(
            @PathVariable Long id,
            @Valid @ModelAttribute("purchase") Purchase updated,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("customers", customerService.getAll());
            model.addAttribute("shops", shopService.getAll());
            return "purchases/edit";
        }

        try {
            Purchase existing = service.getById(id);
            existing.setAmount(updated.getAmount());
            existing.setCustomer(updated.getCustomer());
            existing.setShop(updated.getShop());

            service.save(existing);
            return "redirect:/purchases";

        } catch (IllegalStateException ex) {
            model.addAttribute("purchase", updated);
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("customers", customerService.getAll());
            model.addAttribute("shops", shopService.getAll());
            return "purchases/edit";
        }
    }

    /* ===================== DELETE ===================== */
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/purchases";
    }
}