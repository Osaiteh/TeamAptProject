package com.glory.teamaptproject.exception;

public class ExpiredTokenException extends Exception{
    public ExpiredTokenException(Exception e) {
        super(e);
    }
}
