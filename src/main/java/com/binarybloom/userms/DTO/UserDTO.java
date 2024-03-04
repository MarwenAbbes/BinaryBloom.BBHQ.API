package com.binarybloom.userms.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private LocalDate birthDay;
    private String email;
    private String phoneNumber;
    private String cin;
    private String address;
    private boolean enabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long store_id;
    private Long role_id;
    private Long gender_id;

}
