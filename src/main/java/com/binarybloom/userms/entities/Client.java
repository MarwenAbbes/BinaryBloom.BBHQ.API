package com.binarybloom.userms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String cin;
    private String taxRegistrationNumber;

    private String address;
    private String city;
    private String state;
    private String postalCode;
    private String alternativePhoneNumber;
    private String faxNumber;
    private String companyName;
    private String status;
    @Column(
            name = "created_at",
            updatable = false,
            nullable = false
    )
    private LocalDateTime createdAt;

    @Column(
            name = "updated_at",
            insertable = false
    )
    private LocalDateTime updatedAt;


}
