package com.binarybloom.userms.controllers;

import com.binarybloom.userms.entities.Category;
import com.binarybloom.userms.entities.Supplier;
import com.binarybloom.userms.eums.DataStatus;
import com.binarybloom.userms.repositories.SupplierRepository;
import com.binarybloom.userms.services.LoggerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/suppliers")
public class SupplierController {
    LoggerService logger;
    SupplierRepository supplierRepository;

    public SupplierController(LoggerService logger, SupplierRepository supplierRepository) {
        this.logger = logger;
        this.supplierRepository = supplierRepository;
    }

    @GetMapping
    private ResponseEntity<List<Supplier>> get() {
        List<Supplier> elements = null;
        try {
            elements = supplierRepository.findSupplierByStatus(DataStatus.A.toString());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return new ResponseEntity<>(elements, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<Boolean> insert(@RequestBody Supplier element) {
        try {
            element.setStatus(DataStatus.A.toString());
            supplierRepository.save(element);
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    private ResponseEntity<Boolean> update(@RequestBody Supplier element) {
        try {
            supplierRepository.save(element);
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{supplierId}")
    private ResponseEntity<Boolean> remove(@PathVariable Integer supplierId) {
        try {
            Supplier element = supplierRepository.findById(supplierId).orElse(null);
            assert element != null;
            element.setStatus(DataStatus.D.toString());
            supplierRepository.save(element);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }
}
