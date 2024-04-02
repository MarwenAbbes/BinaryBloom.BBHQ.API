package com.binarybloom.userms.repositories;

import com.binarybloom.userms.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SupplierRepository extends JpaRepository<Supplier,Integer> {
    List<Supplier> findSupplierByStatus(String status);
}
