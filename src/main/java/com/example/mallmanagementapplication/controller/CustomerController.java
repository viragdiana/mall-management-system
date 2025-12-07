package com.example.mallmanagementapplication.controller;

import com.example.mallmanagementapplication.model.Customer;
import com.example.mallmanagementapplication.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    /** LIST */
    @GetMapping
    public String index(Model model) {
        model.addAttribute("customers", service.getAll());
        return "customers/index";
    }

    /** DETAILS */
    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("customer", service.getById(id));
        return "customers/details";
    }

    /** NEW FORM */
    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customers/new";
    }

    /** CREATE */
    @PostMapping
    public String create(
            @Valid @ModelAttribute("customer") Customer customer,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "customers/new";
        }

        try {
            service.save(customer);
        } catch (IllegalStateException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "customers/new";
        }

        return "redirect:/customers";
    }

    /** EDIT FORM */
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("customer", service.getById(id));
        return "customers/edit";
    }

    /** UPDATE */
    @PostMapping("/{id}/edit")
    public String update(
            @PathVariable Long id,
            @Valid @ModelAttribute("customer") Customer updated,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "customers/edit";
        }

        Customer existing = service.getById(id);
        existing.setName(updated.getName());
        existing.setCurrency(updated.getCurrency());
        existing.setEmail(updated.getEmail());

        try {
            service.save(existing);
        } catch (IllegalStateException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "customers/edit";
        }

        return "redirect:/customers";
    }

    /** DELETE */
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/customers";
    }
}
