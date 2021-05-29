package com.glory.teamaptproject.service;

import com.glory.teamaptproject.enums.TransactionType;
import com.glory.teamaptproject.model.Balance;
import com.glory.teamaptproject.repository.BalanceRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BalanceService {

    private final BalanceRepository balanceRepository;

    public BalanceService(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    public Balance fetchCurrentBalance(Long accountNumber) {
        Balance balance = balanceRepository.findByAccountNumber(accountNumber);
        if (balance != null) {
            return balance;
        }
        Balance newUserBalance = new Balance(accountNumber, BigDecimal.ZERO);
        return balanceRepository.save(newUserBalance);
    }

    public boolean isBalanceSufficient(Long accountNumber, BigDecimal amount)  {
        Balance currentBalance = fetchCurrentBalance(accountNumber);
        return currentBalance.getAmount().compareTo(amount) >= 0;
    }

    public Balance updateBalanceByAccountNumber(Long accountNumber, BigDecimal amount, TransactionType transactionType) {
        Balance currentBalance = fetchCurrentBalance(accountNumber);
        BigDecimal balanceAmount = currentBalance.getAmount();

        if (transactionType == TransactionType.CREDIT) {
            BigDecimal updatedBalanceAmount = balanceAmount.add(amount);
            currentBalance.setAmount(updatedBalanceAmount);
            return balanceRepository.save(currentBalance);
        }

        BigDecimal updatedBalanceAmount = balanceAmount.subtract(amount);
        currentBalance.setAmount(updatedBalanceAmount);
        return balanceRepository.save(currentBalance);
    }
}
