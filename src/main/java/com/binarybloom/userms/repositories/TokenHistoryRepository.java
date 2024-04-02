package com.binarybloom.userms.repositories;

import com.binarybloom.userms.entities.TokenHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface TokenHistoryRepository extends JpaRepository<TokenHistory,Integer> {
    Optional<TokenHistory> findTokenHistoriesByToken(String token);
}
