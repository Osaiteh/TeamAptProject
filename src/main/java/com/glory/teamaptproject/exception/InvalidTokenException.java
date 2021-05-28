package com.glory.teamaptproject.exception;

public class InvalidTokenException extends Exception{
    public InvalidTokenException(String message){
        super(message);
    }

    public InvalidTokenException(Exception e){
        super(e);
    }

    public InvalidTokenException(String message, Exception e){
        super(message, e);
    }
}
