package com.binarybloom.userms.repositories;

import com.binarybloom.userms.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {
}
