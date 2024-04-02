package com.binarybloom.userms.entities;

import com.binarybloom.userms.eums.Operation;
import com.binarybloom.userms.eums.PaymentMethods;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Operation operation;
    private Integer operationId;
    @Enumerated(EnumType.STRING)
    private PaymentMethods paymentMethod;
    private Double amount;

    private String status;
}
