package com.binarybloom.userms.controllers;

import com.binarybloom.userms.entities.Category;
import com.binarybloom.userms.entities.Product;
import com.binarybloom.userms.entities.SearchElement;
import com.binarybloom.userms.eums.DataStatus;
import com.binarybloom.userms.repositories.ProductRepository;
import com.binarybloom.userms.services.LoggerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/products")
public class ProductController {

    private LoggerService logger;
    private ProductRepository productRepository;

    public ProductController(LoggerService logger, ProductRepository productRepository) {
        this.logger = logger;
        this.productRepository = productRepository;
    }

    @GetMapping
    private ResponseEntity<List<Product>> get() {
        List<Product> elements = null;
        try {
            elements = productRepository.findProductByStatus(DataStatus.A.toString());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return new ResponseEntity<>(elements, HttpStatus.OK);
    }


    @PutMapping("/search")
    private ResponseEntity<List<Product>> search(@RequestBody SearchElement element) {
        List<Product> elements = new ArrayList<>();
        String queryLower = element.getQuery().toLowerCase().trim();
        try {
            if(queryLower.length() >= 1){
                for (Product product : productRepository.findProductByStatus(DataStatus.A.toString())) {
                    if (product.getName().toLowerCase().contains(queryLower) ||
                            product.getDescription().toLowerCase().contains(queryLower) ||
                            product.getReference().toLowerCase().contains(queryLower) ||
                            product.getBarCode().toLowerCase().contains(queryLower)) {
                        elements.add(product);
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return new ResponseEntity<>(elements, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<Boolean> insert(@RequestBody Product element) {
        try {
            element.setStatus(DataStatus.A.toString());
            element.setCreatedAt(LocalDateTime.now());
            productRepository.save(element);
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    private ResponseEntity<Boolean> update(@RequestBody Product element) {
        try {
            productRepository.save(element);
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{productId}")
    private ResponseEntity<Boolean> remove(@PathVariable Integer productId) {
        try {
            Product element = productRepository.findById(productId).orElse(null);
            assert element != null;
            element.setStatus(DataStatus.D.toString());
            productRepository.save(element);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }
}
