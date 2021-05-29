package com.glory.teamaptproject.controller;

import com.glory.teamaptproject.dto.request.TransactionRequest;
import com.glory.teamaptproject.exception.InsufficientBalanceException;
import com.glory.teamaptproject.model.Transaction;
import com.glory.teamaptproject.model.User;
import com.glory.teamaptproject.service.TransactionService;
import com.glory.teamaptproject.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;
    private final UserService userService;

    public TransactionController(TransactionService transactionService, UserService userService) {
        this.transactionService = transactionService;
        this.userService = userService;
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public Transaction newTransaction(@RequestBody TransactionRequest transactionRequest, Principal principal) throws InsufficientBalanceException {
        User user = userService.findUserByUsername(principal.getName());

        return transactionService.newTransaction(transactionRequest, user.getAccountNumber());
    }
}
