package com.example.grocery.service;

import com.example.grocery.entity.Customer;
import com.example.grocery.exception.ResourceNotFoundException;
import com.example.grocery.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository repository;

    @InjectMocks
    private CustomerService service;

    @Test
    void findByIdReturnsCustomerWhenPresent() {
        Customer customer = new Customer("Ajay", "ajay@example.com", "Pune", "9876543210");
        when(repository.findById(1L)).thenReturn(Optional.of(customer));

        Customer result = service.findById(1L);

        assertSame(customer, result);
    }

    @Test
    void findByIdThrowsWhenCustomerDoesNotExist() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.findById(1L));
    }
}
