package com.example.grocery.service;

import com.example.grocery.entity.GroceryItem;
import com.example.grocery.exception.ResourceNotFoundException;
import com.example.grocery.repository.GroceryItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GroceryItemServiceTest {

    @Mock
    private GroceryItemRepository repository;

    @InjectMocks
    private GroceryItemService service;

    @Test
    void findByIdReturnsItemWhenPresent() {
        GroceryItem item = new GroceryItem(
                "Rice", "Grains", BigDecimal.valueOf(60), 10);
        when(repository.findById(1L)).thenReturn(Optional.of(item));

        assertSame(item, service.findById(1L));
    }

    @Test
    void findByIdThrowsWhenItemDoesNotExist() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.findById(1L));
    }
}
