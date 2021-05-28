package com.glory.teamaptproject.repository;

import com.glory.teamaptproject.enums.TransactionType;
import com.glory.teamaptproject.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction findFirstByReferenceAndTransactionType(UUID reference, TransactionType type);
}
