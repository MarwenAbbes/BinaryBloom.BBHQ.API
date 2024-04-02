package com.binarybloom.userms.repositories;

import com.binarybloom.userms.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findProductByStatus(String status);
}
