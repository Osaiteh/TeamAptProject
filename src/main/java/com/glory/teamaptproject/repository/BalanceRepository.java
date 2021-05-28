package com.glory.teamaptproject.repository;

import com.glory.teamaptproject.model.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {
    Balance findByAccountNumber(Long accountNumber);
}
