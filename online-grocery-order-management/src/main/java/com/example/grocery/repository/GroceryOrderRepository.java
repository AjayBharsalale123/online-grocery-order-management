package com.example.grocery.repository;

import com.example.grocery.entity.GroceryOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroceryOrderRepository extends JpaRepository<GroceryOrder, Long> {
}
