package com.binarybloom.userms.repositories;

import com.binarybloom.userms.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientRepository extends JpaRepository<Client,Integer> {
    List<Client> findClientByStatus(String status);
}
