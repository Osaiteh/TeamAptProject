package com.glory.teamaptproject.controller;

import com.glory.teamaptproject.model.Balance;
import com.glory.teamaptproject.model.User;
import com.glory.teamaptproject.service.BalanceService;
import com.glory.teamaptproject.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/balance")
public class BalanceController {

    private final BalanceService balanceService;
    private final UserService userService;

    public BalanceController(BalanceService balanceService, UserService userService) {
        this.balanceService = balanceService;
        this.userService = userService;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public Balance getAccountBalance(Principal principal) {
        User user = userService.findUserByUsername(principal.getName());
        return balanceService.fetchCurrentBalance(user.getAccountNumber());
    }
}
