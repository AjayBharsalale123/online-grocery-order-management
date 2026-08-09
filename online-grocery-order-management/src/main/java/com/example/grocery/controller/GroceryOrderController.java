package com.example.grocery.controller;

import com.example.grocery.dto.OrderRequest;
import com.example.grocery.entity.GroceryOrder;
import com.example.grocery.service.GroceryOrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class GroceryOrderController {

    private final GroceryOrderService service;

    public GroceryOrderController(GroceryOrderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<GroceryOrder> create(@Valid @RequestBody OrderRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping
    public List<GroceryOrder> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public GroceryOrder findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public GroceryOrder update(
            @PathVariable Long id,
            @Valid @RequestBody OrderRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
