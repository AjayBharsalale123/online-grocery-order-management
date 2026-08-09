package com.example.grocery.service;

import com.example.grocery.entity.Customer;
import com.example.grocery.exception.ResourceNotFoundException;
import com.example.grocery.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer create(Customer customer) {
        customer.setId(null);
        return repository.save(customer);
    }

    public List<Customer> findAll() {
        return repository.findAll();
    }

    public Customer findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer not found with id: " + id));
    }

    public Customer update(Long id, Customer request) {
        Customer customer = findById(id);
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setAddress(request.getAddress());
        customer.setPhone(request.getPhone());
        return repository.save(customer);
    }

    public void delete(Long id) {
        Customer customer = findById(id);
        repository.delete(customer);
    }
}
