package com.glory.teamaptproject.dto.response;

public class LoginResponse {
    private String username;
    private Long accountNumber;
    private String token;

    public LoginResponse(String username, Long accountNumber, String token) {
        this.username = username;
        this.accountNumber = accountNumber;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }
}
