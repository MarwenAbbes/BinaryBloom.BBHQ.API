package com.binarybloom.userms.entities;

import com.binarybloom.userms.eums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    @JoinColumn()
    private Client client;

    private LocalDateTime orderDate;
    @OneToMany
    @JoinColumn()
    private List<OrderItem> orders;
    private double totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private String shippingAddress;


    @OneToOne
    @JoinColumn()
    private TransactionLog transactionDetails;
    @Column(
            name = "created_at",
            updatable = false,
            nullable = false
    )
    private LocalDateTime createdAt;

        private String status;
}
