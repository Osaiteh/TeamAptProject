package com.glory.teamaptproject.controller;

import com.glory.teamaptproject.dto.request.TransactionRequest;
import com.glory.teamaptproject.exception.InsufficientBalanceException;
import com.glory.teamaptproject.model.Transaction;
import com.glory.teamaptproject.service.TransactionService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public Transaction newTransaction(@RequestBody TransactionRequest transactionRequest) throws InsufficientBalanceException {
        return transactionService.newTransaction(transactionRequest);
    }
}
