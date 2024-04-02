package com.binarybloom.userms.controllers;

import com.binarybloom.userms.entities.Category;
import com.binarybloom.userms.entities.Role;
import com.binarybloom.userms.eums.DataStatus;
import com.binarybloom.userms.repositories.CategoryRepository;
import com.binarybloom.userms.services.LoggerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/categories")
public class CategoryController {

    LoggerService logger;
    CategoryRepository categoryRepository;

    public CategoryController(LoggerService logger, CategoryRepository categoryRepository) {
        this.logger = logger;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    private ResponseEntity<List<Category>> get() {
        List<Category> elements = null;
        try {
            elements = categoryRepository.findCategoryByStatus(DataStatus.A.toString());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return new ResponseEntity<>(elements, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<Boolean> insert(@RequestBody Category element) {
        try {
            element.setStatus(DataStatus.A.toString());
            categoryRepository.save(element);
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    private ResponseEntity<Boolean> update(@RequestBody Category element) {
        try {
            categoryRepository.save(element);
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{categoryId}")
    private ResponseEntity<Boolean> remove(@PathVariable Integer categoryId) {
        try {
            Category element = categoryRepository.findById(categoryId).orElse(null);
            assert element != null;
            element.setStatus(DataStatus.D.toString());
            categoryRepository.save(element);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

}
