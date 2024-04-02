package com.binarybloom.userms.controllers;

import com.binarybloom.userms.DTO.StoreDTO;
import com.binarybloom.userms.entities.Store;
import com.binarybloom.userms.eums.DataStatus;
import com.binarybloom.userms.repositories.StoreRepository;
import com.binarybloom.userms.services.LoggerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/stores")
public class StoreController {
    LoggerService logger;
    private StoreRepository storeRepository;

    public StoreController(StoreRepository storeRepository, LoggerService logger) {
        this.storeRepository = storeRepository;
        this.logger = logger;
    }

    @GetMapping
    private ResponseEntity<List<Store>> get() {
        List<Store> stores = storeRepository.findStoreByStatus(DataStatus.A.toString());
        return new ResponseEntity<>(stores, stores.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<Boolean> insert(@RequestBody Store _store) {
        try {
            storeRepository.save(_store);
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{storeId}")
    private ResponseEntity<Boolean> remove(@PathVariable Integer storeId) {
        try {
            Store store = storeRepository.findById(storeId).orElse(null);
            assert store != null;
            store.setStatus(DataStatus.D.toString());
            storeRepository.save(store);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    private ResponseEntity<Boolean> update(@RequestBody Store _store) {
        try {
            logger.info(_store.getStatus());
            storeRepository.save(_store);
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_FOUND);
        }
    }
}
