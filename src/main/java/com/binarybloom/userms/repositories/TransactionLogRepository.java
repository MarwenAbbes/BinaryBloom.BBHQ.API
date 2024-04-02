package com.binarybloom.userms.repositories;

import com.binarybloom.userms.entities.TransactionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface TransactionLogRepository extends JpaRepository<TransactionLog,Integer> {
}
