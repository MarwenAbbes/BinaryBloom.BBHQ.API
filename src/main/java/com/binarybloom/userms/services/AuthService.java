package com.binarybloom.userms.services;

import com.binarybloom.userms.entities.TokenHistory;
import com.binarybloom.userms.entities.User;
import com.binarybloom.userms.repositories.TokenHistoryRepository;
import com.binarybloom.userms.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository repository;
    private final TokenHistoryRepository tokenHistoryRepository;


    public AuthService(UserRepository repository, TokenHistoryRepository tokenHistoryRepository) {
        this.repository = repository;
        this.tokenHistoryRepository = tokenHistoryRepository;
    }

    public User authenticate(User request) {

        User user = repository.findByUsername(request.getUsername()).orElseThrow();
        if (repository.existsByUsername(request.getUsername())) {
            return (repository.findByUsername(request.getUsername()).orElseThrow());
        } else
            return null;

    }

    public String logout(User user, String token) {
        TokenHistory tokenHistory = tokenHistoryRepository.findTokenHistoriesByToken(token).orElseThrow();
        tokenHistory.setActive(false);
        tokenHistoryRepository.save(tokenHistory);
        return "Logged out";
    }
}
