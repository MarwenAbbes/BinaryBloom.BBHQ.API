package com.binarybloom.userms.repositories;

import com.binarybloom.userms.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findCategoryByStatus(String status);
}
