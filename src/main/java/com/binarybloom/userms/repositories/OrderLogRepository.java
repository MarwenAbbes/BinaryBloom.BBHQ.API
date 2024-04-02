package com.binarybloom.userms.repositories;

import com.binarybloom.userms.entities.OrderLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface OrderLogRepository extends JpaRepository<OrderLog,Integer> {
}
