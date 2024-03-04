package com.binarybloom.userms.controllers;

import com.binarybloom.userms.entities.User;
import com.binarybloom.userms.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping
    public ResponseEntity<Boolean> authenticate(@RequestBody User user) {
        //return ResponseEntity.ok(authService.authenticate(user));
        return ResponseEntity.ok(true);
    }

    @PostMapping("/logout/{token}")
    public ResponseEntity<String> logOut(@RequestBody User user, @PathVariable String token) {
        return ResponseEntity.ok(authService.logout(user, token));
    }
}
