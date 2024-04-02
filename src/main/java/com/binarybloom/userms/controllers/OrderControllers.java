package com.binarybloom.userms.controllers;

import com.binarybloom.userms.entities.Category;
import com.binarybloom.userms.entities.OrderLog;
import com.binarybloom.userms.eums.DataStatus;
import com.binarybloom.userms.repositories.OrderItemRepository;
import com.binarybloom.userms.repositories.OrderLogRepository;
import com.binarybloom.userms.repositories.TransactionLogRepository;
import com.binarybloom.userms.services.LoggerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/orders")
public class OrderControllers {
    LoggerService logger;
    OrderItemRepository orderItemRepository;
    OrderLogRepository orderLogRepository;
    TransactionLogRepository transactionLogRepository;

    public OrderControllers(LoggerService logger, OrderItemRepository orderItemRepository, OrderLogRepository orderLogRepository, TransactionLogRepository transactionLogRepository) {
        this.logger = logger;
        this.orderItemRepository = orderItemRepository;
        this.orderLogRepository = orderLogRepository;
        this.transactionLogRepository = transactionLogRepository;
    }

    @GetMapping
    private ResponseEntity<List<OrderLog>> get() {
        List<OrderLog> elements = null;
        try {
            elements = orderLogRepository.findAll();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return new ResponseEntity<>(elements, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<Boolean> insert(@RequestBody OrderLog element) {
        try {
            element.setStatus(DataStatus.A.toString());
            orderLogRepository.save(element);
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.BAD_REQUEST);
        }
    }
}
