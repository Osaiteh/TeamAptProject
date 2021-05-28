package com.glory.teamaptproject.dto.response;

public class SignupResponse {
    private String username;
    private Long accountNumber;

    public SignupResponse(String username, Long accountNumber) {
        this.username = username;
        this.accountNumber = accountNumber;
    }
}
