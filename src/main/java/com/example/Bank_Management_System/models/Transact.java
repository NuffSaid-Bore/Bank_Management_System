package com.example.Bank_Management_System.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Transact")
public class Transact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transaction_id;

    private int account_id;
    private String transaction_type;

    // Use BigDecimal for the amount field to avoid precision issues
    private BigDecimal amount;

    private String source;
    private String status;
    private String reason_code;
    private LocalDateTime created_at;

    @PrePersist
    public void prePersist() {
        // Ensure created_at is set to the current time if it's not already provided
        if (created_at == null) {
            created_at = LocalDateTime.now();
        }
    }
}