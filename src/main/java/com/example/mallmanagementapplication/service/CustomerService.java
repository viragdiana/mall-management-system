package com.example.mallmanagementapplication.service;

import com.example.mallmanagementapplication.model.Customer;
import com.example.mallmanagementapplication.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.mallmanagementapplication.service.Validation.requireExists;

@Service
public class CustomerService {

    private final CustomerRepository customerRepo;

    public CustomerService(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    public void addCustomer(Customer customer) {
        customerRepo.save(customer);
    }

    public Customer getCustomer(String id) {
        return requireExists(customerRepo, id, "Customer");
    }

    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    public void deleteCustomer(String id) {
        requireExists(customerRepo, id, "Customer");
        customerRepo.delete(id);
    }
}
