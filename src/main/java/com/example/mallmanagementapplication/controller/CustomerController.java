package com.example.mallmanagementapplication.controller;


import com.example.mallmanagementapplication.model.Customer;
import com.example.mallmanagementapplication.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    // GET /customers → show all customers
    @GetMapping
    public String showAllCustomers(Model model) {
        model.addAttribute("customers", service.getAllCustomers());
        return "customers/index"; // points to templates/customers/index.html
    }

    // GET /customers/new → show form for adding new customer
    @GetMapping("/new")
    public String showCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customers/form"; // templates/customers/form.html
    }

    // POST /customers → create new customer
    @PostMapping
    public String addCustomer(@ModelAttribute Customer customer) {
        service.addCustomer(customer);
        return "redirect:/customers"; // after saving, go back to list
    }

    // POST /customers/{id}/delete → delete a customer
    @PostMapping("/{id}/delete")
    public String deleteCustomer(@PathVariable String id) {
        service.deleteCustomer(id);
        return "redirect:/customers";
    }
}