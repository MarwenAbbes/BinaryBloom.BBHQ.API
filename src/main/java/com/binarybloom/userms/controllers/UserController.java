package com.binarybloom.userms.controllers;

import com.binarybloom.userms.DTO.UserDTO;
import com.binarybloom.userms.entities.User;
import com.binarybloom.userms.eums.DataStatus;
import com.binarybloom.userms.repositories.GenderRepository;
import com.binarybloom.userms.repositories.RoleRepository;
import com.binarybloom.userms.repositories.StoreRepository;
import com.binarybloom.userms.repositories.UserRepository;
import com.binarybloom.userms.services.LoggerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private StoreRepository storeRepository;
    private GenderRepository genderRepository;
    private LoggerService logger;

    public UserController(UserRepository userRepository, RoleRepository roleRepository, StoreRepository storeRepository, GenderRepository genderRepository, LoggerService logger) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.storeRepository = storeRepository;
        this.genderRepository = genderRepository;
        this.logger = logger;
    }

    @GetMapping
    private ResponseEntity<List<User>> get() {
        List<User> users = userRepository.findUserByStatus(DataStatus.A.toString());
        return new ResponseEntity<>(users, users.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    private ResponseEntity<User> getById(@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        return new ResponseEntity<>(user, user.equals(null) ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<User> insert(@RequestBody UserDTO userDTO) {
        try {

            User user = User.builder()
                    .name(userDTO.getName())
                    .surname(userDTO.getSurname())
                    .gender( genderRepository.findById(userDTO.getGender_id()).orElse(null))
                    .id(userDTO.getId())
                    .username(userDTO.getUsername())
                    .password(userDTO.getPassword())
                    .birthDay(userDTO.getBirthDay())
                    .email(userDTO.getEmail())
                    .phoneNumber(userDTO.getPhoneNumber())
                    .cin(userDTO.getCin())
                    .address((userDTO.getAddress()))
                    .enabled(true)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(userDTO.getUpdatedAt())
                    .store(storeRepository.findById(userDTO.getStore_id()).orElse(null))
                    .role(roleRepository.findById(userDTO.getRole_id()).orElse(null))
                    .status(DataStatus.A.toString())
                    .build();
            userRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    private ResponseEntity<User> update(@RequestBody UserDTO userDTO) {
        try {
            User user = User.builder()
                    .id(userDTO.getId())
                    .name(userDTO.getName())
                    .gender( genderRepository.findById(userDTO.getGender_id()).orElse(null))
                    .surname(userDTO.getSurname())
                    .username(userDTO.getUsername())
                    .password(userDTO.getPassword())
                    .birthDay(userDTO.getBirthDay())
                    .email(userDTO.getEmail())
                    .phoneNumber(userDTO.getPhoneNumber())
                    .cin(userDTO.getCin())
                    .address((userDTO.getAddress()))
                    .enabled(userDTO.isEnabled())
                    .createdAt(userDTO.getCreatedAt())
                    .updatedAt(LocalDateTime.now())
                    .store(storeRepository.findById(userDTO.getStore_id()).orElse(null))
                    .role(roleRepository.findById(userDTO.getRole_id()).orElse(null))
                    .status(DataStatus.A.toString())
                    .build();
            userRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userId}")
    private ResponseEntity<Boolean> remove(@PathVariable Long userId) {
        try {
            User user = userRepository.findById(userId).orElse(null);
            assert user != null;
            user.setStatus(DataStatus.D.toString());
            userRepository.save(user);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }
}
