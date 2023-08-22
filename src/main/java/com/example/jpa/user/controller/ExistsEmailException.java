package com.example.jpa.user.controller;

public class ExistsEmailException extends RuntimeException {
    public ExistsEmailException(String message) {
        super(message);
    }
}
