package com.binarybloom.userms.repositories;

import com.binarybloom.userms.entities.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface GenderRepository extends JpaRepository<Gender,Integer> {
}
