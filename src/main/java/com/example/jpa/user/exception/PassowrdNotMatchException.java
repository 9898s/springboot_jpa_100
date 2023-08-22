package com.example.jpa.user.exception;

public class PassowrdNotMatchException extends RuntimeException {
    public PassowrdNotMatchException(String message) {
        super(message);
    }
}
