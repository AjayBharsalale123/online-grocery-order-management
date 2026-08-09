package com.example.grocery.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class OrderRequest {

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotEmpty(message = "At least one grocery item is required")
    private List<Long> groceryItemIds;

    private LocalDate orderDate;

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }
    public List<Long> getGroceryItemIds() { return groceryItemIds; }
    public void setGroceryItemIds(List<Long> groceryItemIds) { this.groceryItemIds = groceryItemIds; }
    public LocalDate getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDate orderDate) { this.orderDate = orderDate; }
}
