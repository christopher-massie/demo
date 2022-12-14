package com.example.jpa;

public class InvalidCSVRecordException extends RuntimeException {
    public InvalidCSVRecordException(String message) {
        super(message);
    }
}
