package com.glory.teamaptproject.repository;

import com.glory.teamaptproject.enums.TransactionType;
import com.glory.teamaptproject.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction findByReferenceAndTransactionType(String reference, TransactionType type);
}
