package com.example.exception;

public class FetchException extends RuntimeException {
    public FetchException(String message) {
        super(message);
    }
}
