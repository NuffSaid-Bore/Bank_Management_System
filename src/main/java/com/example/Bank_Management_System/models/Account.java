package com.example.Bank_Management_System.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Accounts")
public class Account {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int account_id;
    private int user_id;
    private String account_number;
    private String account_name;
    private String account_type;
    
    // Default balance to 0.00
    private BigDecimal balance = BigDecimal.ZERO;

    // Default created_at to current timestamp
    private LocalDateTime created_at = LocalDateTime.now();

    private LocalDateTime updated_at;
}
