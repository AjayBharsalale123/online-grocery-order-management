package com.example.grocery.service;

import com.example.grocery.dto.OrderRequest;
import com.example.grocery.entity.Customer;
import com.example.grocery.entity.GroceryItem;
import com.example.grocery.entity.GroceryOrder;
import com.example.grocery.exception.ResourceNotFoundException;
import com.example.grocery.repository.CustomerRepository;
import com.example.grocery.repository.GroceryItemRepository;
import com.example.grocery.repository.GroceryOrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class GroceryOrderService {

    private final GroceryOrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final GroceryItemRepository itemRepository;

    public GroceryOrderService(
            GroceryOrderRepository orderRepository,
            CustomerRepository customerRepository,
            GroceryItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.itemRepository = itemRepository;
    }

    public GroceryOrder create(OrderRequest request) {
        GroceryOrder order = new GroceryOrder();
        applyRequest(order, request);
        return orderRepository.save(order);
    }

    public List<GroceryOrder> findAll() {
        return orderRepository.findAll();
    }

    public GroceryOrder findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Order not found with id: " + id));
    }

    public GroceryOrder update(Long id, OrderRequest request) {
        GroceryOrder order = findById(id);
        applyRequest(order, request);
        return orderRepository.save(order);
    }

    public void delete(Long id) {
        GroceryOrder order = findById(id);
        orderRepository.delete(order);
    }

    private void applyRequest(GroceryOrder order, OrderRequest request) {
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer not found with id: " + request.getCustomerId()));

        List<GroceryItem> items = itemRepository.findAllById(request.getGroceryItemIds());

        if (items.size() != request.getGroceryItemIds().stream().distinct().count()) {
            throw new ResourceNotFoundException("One or more grocery items were not found");
        }

        order.setCustomer(customer);
        order.setGroceryItems(items);
        order.setOrderDate(request.getOrderDate() != null
                ? request.getOrderDate()
                : LocalDate.now());
        order.setTotalPrice(
                items.stream()
                        .map(GroceryItem::getPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
        );
    }
}
