package com.example.mallmanagementapplication.controller;

import com.example.mallmanagementapplication.model.Customer;
import com.example.mallmanagementapplication.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("customers", service.getAll());
        return "customers/index";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("customer", service.getById(id));
        return "customers/details";
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("customer", new Customer());
        return "customers/form";
    }

    @PostMapping
    public String create(@ModelAttribute Customer customer) {
        service.save(customer);
        return "redirect:/customers";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("customer", service.getById(id));
        return "customers/edit";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute Customer updated) {
        Customer existing = service.getById(id);

        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        existing.setCurrency(updated.getCurrency());

        service.save(existing);
        return "redirect:/customers";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/customers";
    }
}
