package com.binarybloom.userms.repositories;

import com.binarybloom.userms.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findStoreByStatus(String status);
}
