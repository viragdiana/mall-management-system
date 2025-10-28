package com.example.mallmanagementapplication.repository;
import com.example.mallmanagementapplication.model.Customer;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class InMemoryCustomerRepository implements CustomerRepository {
    private final Map<String, Customer> customers = new HashMap<>();

    @Override
    public void save(Customer customer) {
        customers.put(customer.getId(), customer);
    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public Optional<Customer> findById(String id) {
        return Optional.ofNullable(customers.get(id));
    }

    @Override
    public void delete(String id) {
        customers.remove(id);
    }
}
