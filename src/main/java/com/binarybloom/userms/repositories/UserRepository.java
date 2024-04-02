package com.binarybloom.userms.repositories;

import com.binarybloom.userms.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    List<User> findUserByStatus(String status);

    Boolean existsByUsername(String username);
}
