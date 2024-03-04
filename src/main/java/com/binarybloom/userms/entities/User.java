package com.binarybloom.userms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
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

    @ManyToOne
    @JoinColumn()
    private Store store;

    @ManyToOne
    @JoinColumn()
    private Role role;
    private String status;

    @ManyToOne
    @JoinColumn()
    private Gender gender;

}
