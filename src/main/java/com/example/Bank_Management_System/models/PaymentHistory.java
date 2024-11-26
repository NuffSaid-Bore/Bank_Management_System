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
@Table(name = "v_payments") 
public class PaymentHistory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int payment_id;
    
    private int account_id;
    private int user_id;
    private String beneficiary;
    private String beneficiary_acc_no;
    
    // Use BigDecimal for better precision in amounts
    private BigDecimal amount;
    
    private String status;
    private String reference_no;
    private String reason_code;
    private LocalDateTime created_at;
    
    @PrePersist
    public void prePersist() {
        // Ensure created_at is set to the current time if it's not provided
        if (created_at == null) {
            created_at = LocalDateTime.now();
        }
    }
}