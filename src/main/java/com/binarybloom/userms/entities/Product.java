package com.binarybloom.userms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;
    private String reference;
    private String barCode;

    @ManyToOne
    @JoinColumn()
    private Category category;

    private String cost;
    private String price;
    private Integer quantity;
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
