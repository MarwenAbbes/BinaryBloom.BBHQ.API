package com.binarybloom.userms.controllers;

import com.binarybloom.userms.entities.Role;
import com.binarybloom.userms.eums.DataStatus;
import com.binarybloom.userms.repositories.RoleRepository;
import com.binarybloom.userms.services.LoggerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/roles")
public class RoleController {

    LoggerService logger;
    private RoleRepository roleRepository;

    public RoleController(RoleRepository roleRepository, LoggerService logger) {
        this.roleRepository = roleRepository;
        this.logger = logger;
    }

    @GetMapping
    private ResponseEntity<List<Role>> get() {
        List<Role> roles = roleRepository.findRoleByStatus(DataStatus.A.toString());
        return new ResponseEntity<>(roles, roles.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<Boolean> insert(@RequestBody Role role) {
        try {
            role.setStatus(DataStatus.A.toString());
            roleRepository.save(role);
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    private ResponseEntity<Boolean> update(@RequestBody Role role) {
        try {
            roleRepository.save(role);
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{roleId}")
    private ResponseEntity<Boolean> remove(@PathVariable Long roleId) {
        try {
            Role role = roleRepository.findById(roleId).orElse(null);
            assert role != null;
            role.setStatus(DataStatus.D.toString());
            roleRepository.save(role);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }
}
