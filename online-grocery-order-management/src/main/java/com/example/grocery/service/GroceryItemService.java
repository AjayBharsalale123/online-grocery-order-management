package com.example.grocery.service;

import com.example.grocery.entity.GroceryItem;
import com.example.grocery.exception.ResourceNotFoundException;
import com.example.grocery.repository.GroceryItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroceryItemService {

    private final GroceryItemRepository repository;

    public GroceryItemService(GroceryItemRepository repository) {
        this.repository = repository;
    }

    public GroceryItem create(GroceryItem item) {
        item.setId(null);
        return repository.save(item);
    }

    public List<GroceryItem> findAll() {
        return repository.findAll();
    }

    public GroceryItem findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Grocery item not found with id: " + id));
    }

    public GroceryItem update(Long id, GroceryItem request) {
        GroceryItem item = findById(id);
        item.setName(request.getName());
        item.setCategory(request.getCategory());
        item.setPrice(request.getPrice());
        item.setQuantity(request.getQuantity());
        return repository.save(item);
    }

    public void delete(Long id) {
        GroceryItem item = findById(id);
        repository.delete(item);
    }
}
