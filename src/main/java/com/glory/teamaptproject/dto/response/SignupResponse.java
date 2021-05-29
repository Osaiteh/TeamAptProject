package com.glory.teamaptproject.dto.response;

public class SignupResponse {
    private String username;
    private Long accountNumber;

    public SignupResponse(String username, Long accountNumber) {
        this.username = username;
        this.accountNumber = accountNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }
}
