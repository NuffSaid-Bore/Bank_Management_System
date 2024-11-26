package com.example.Bank_Management_System.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.Bank_Management_System.models.Transact;

@Repository
public interface TransactRepository extends CrudRepository<Transact, Integer> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO v_transaction_history(account_id, user_id, transaction_type, amount, source, status, reason_code, created_at)" +
            "VALUES(:account_id, :user_id, :transact_type, :amount, :source, :status, :reason_code, :created_at)", nativeQuery = true)
    void logTransaction(@Param("account_id")int account_id,
                        @Param("user_id") int user_id,
                        @Param("transact_type")String transact_type,
                        @Param("amount")double amount,
                        @Param("source")String source,
                        @Param("status")String status,
                        @Param("reason_code")String reason_code,
                        @Param("created_at") LocalDateTime created_at);

}