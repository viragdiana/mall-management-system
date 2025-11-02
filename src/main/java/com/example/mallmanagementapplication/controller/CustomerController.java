package com.example.mallmanagementapplication.controller;

import com.example.mallmanagementapplication.model.Customer;
import com.example.mallmanagementapplication.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return service.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable String id) {
        return service.getCustomer(id);
    }

    @PostMapping
    public void addCustomer(@RequestBody Customer customer) {
        service.addCustomer(customer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable String id) {
        service.deleteCustomer(id);
    }
}