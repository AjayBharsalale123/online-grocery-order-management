package com.example.grocery.controller;

import com.example.grocery.entity.GroceryItem;
import com.example.grocery.service.GroceryItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grocery-items")
public class GroceryItemController {

    private final GroceryItemService service;

    public GroceryItemController(GroceryItemService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<GroceryItem> create(@Valid @RequestBody GroceryItem item) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(item));
    }

    @GetMapping
    public List<GroceryItem> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public GroceryItem findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public GroceryItem update(@PathVariable Long id, @Valid @RequestBody GroceryItem item) {
        return service.update(id, item);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
