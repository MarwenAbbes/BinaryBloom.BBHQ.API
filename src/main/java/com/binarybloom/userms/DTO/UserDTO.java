package com.binarybloom.userms.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserDTO {
    private Integer id;
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
    private Integer store_id;
    private Integer role_id;
    private Integer gender_id;

}
