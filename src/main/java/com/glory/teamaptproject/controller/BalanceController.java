package com.glory.teamaptproject.controller;

import com.glory.teamaptproject.model.Balance;
import com.glory.teamaptproject.service.BalanceService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/balance")
public class BalanceController {

    private final BalanceService balanceService;

    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @RequestMapping(path = "/{accountNumber}", method = RequestMethod.GET)
    public Balance getAccountBalance(@PathVariable Long accountNumber) {
        return balanceService.fetchCurrentBalance(accountNumber);
    }
}
