package com.glory.teamaptproject.service;

import com.glory.teamaptproject.dto.request.TransactionRequest;
import com.glory.teamaptproject.enums.TransactionType;
import com.glory.teamaptproject.exception.InsufficientBalanceException;
import com.glory.teamaptproject.model.Balance;
import com.glory.teamaptproject.model.Transaction;
import com.glory.teamaptproject.repository.TransactionRepository;
import com.glory.teamaptproject.utils.AppUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final BalanceService balanceService;

    public TransactionService(TransactionRepository transactionRepository, BalanceService balanceService) {
        this.transactionRepository = transactionRepository;
        this.balanceService = balanceService;
    }

    @Transactional
    public Transaction newTransaction(TransactionRequest transactionRequest, long sourceAccountNumber) throws InsufficientBalanceException {
        Transaction existingTransaction = transactionRepository.findByReferenceAndTransactionType(transactionRequest.getReference().toString(), TransactionType.DEBIT);
        if (existingTransaction != null) {
            // transaction with this transaction reference has been processed before
            return existingTransaction;
        }

        if (!balanceService.isBalanceSufficient(sourceAccountNumber, transactionRequest.getAmount()))  {
            throw new InsufficientBalanceException("Insufficient balance to carry out operation");
        }

        Transaction sourceTransaction = createDebitTransaction(sourceAccountNumber, transactionRequest);
        Balance senderUpdatedBalance = balanceService.updateBalanceByAccountNumber(sourceAccountNumber, transactionRequest.getAmount(), TransactionType.DEBIT);
        Transaction destinationTransaction =createCreditTransaction(transactionRequest);
        Balance recipientUpdatedBalance = balanceService.updateBalanceByAccountNumber(transactionRequest.getDestinationAccountNumber(), transactionRequest.getAmount(), TransactionType.CREDIT);

        return sourceTransaction;
    }

    private Transaction createDebitTransaction(Long sourceAccountNumber, TransactionRequest transactionDto){
        Transaction transaction = createBaseTransaction(transactionDto);
        transaction.setAccountNumber(sourceAccountNumber);
        transaction.setTransactionType(TransactionType.DEBIT);

        return transactionRepository.save(transaction);
    }

    private Transaction createCreditTransaction(TransactionRequest transactionDto){
        Transaction transaction = createBaseTransaction(transactionDto);
        transaction.setAccountNumber(transactionDto.getDestinationAccountNumber());
        transaction.setTransactionType(TransactionType.CREDIT);

        return transactionRepository.save(transaction);
    }

    private Transaction createBaseTransaction(TransactionRequest transactionDto) {
        Transaction transaction = new Transaction();

        transaction.setAmount(transactionDto.getAmount());
        transaction.setReference(transactionDto.getReference().toString());
        transaction.setDescription(transactionDto.getDescription());
        transaction.setDateCreated(AppUtils.getCurrentTime());

        return transaction;
    }
}
