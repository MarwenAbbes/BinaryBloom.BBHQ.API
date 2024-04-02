package com.binarybloom.userms.controllers;

import com.binarybloom.userms.entities.Client;
import com.binarybloom.userms.eums.DataStatus;
import com.binarybloom.userms.repositories.ClientRepository;
import com.binarybloom.userms.services.LoggerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/clients")
public class ClientController {
    private LoggerService logger;
    private ClientRepository clientRepository;

    public ClientController(LoggerService logger, ClientRepository clientRepository) {
        this.logger = logger;
        this.clientRepository = clientRepository;
    }

    @GetMapping
    private ResponseEntity<List<Client>> get() {
        List<Client> elements = null;
        try {
            elements = clientRepository.findClientByStatus(DataStatus.A.toString());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return new ResponseEntity<>(elements, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<Boolean> insert(@RequestBody Client element) {
        try {
            element.setStatus(DataStatus.A.toString());
            element.setCreatedAt(LocalDateTime.now());
            clientRepository.save(element);
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    private ResponseEntity<Boolean> update(@RequestBody Client element) {
        try {
            element.setUpdatedAt(LocalDateTime.now());
            clientRepository.save(element);
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{clientId}")
    private ResponseEntity<Boolean> remove(@PathVariable Integer clientId) {
        try {
            Client element = clientRepository.findById(clientId).orElse(null);
            assert element != null;
            element.setStatus(DataStatus.D.toString());
            clientRepository.save(element);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }
}
