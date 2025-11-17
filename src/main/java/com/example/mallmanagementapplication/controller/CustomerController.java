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

    // LIST ALL
    @GetMapping
    public String index(Model model) {
        model.addAttribute("customers", service.getAllCustomers());
        return "customers/index";
    }

    // DETAILS
    @GetMapping("/{id}")
    public String details(@PathVariable String id, Model model) {
        model.addAttribute("customer", service.getCustomer(id));
        return "customers/details";
    }

    // NEW FORM
    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("customer", new Customer());
        return "customers/form";
    }

    // CREATE
    @PostMapping
    public String create(@ModelAttribute Customer customer) {
        service.addCustomer(customer);
        return "redirect:/customers";
    }

    // EDIT FORM
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable String id, Model model) {
        model.addAttribute("customer", service.getCustomer(id));
        return "customers/edit";
    }

    // UPDATE
    @PostMapping("/{id}/edit")
    public String update(@PathVariable String id, @ModelAttribute Customer updated) {
        Customer existing = service.getCustomer(id);

        existing.setName(updated.getName());
        existing.setCurrency(updated.getCurrency());
        existing.setEmail(updated.getEmail());

        service.addCustomer(existing);
        return "redirect:/customers";
    }

    // DELETE
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.deleteCustomer(id);
        return "redirect:/customers";
    }
}
