package com.example.demo.FieldOps.Exception;

public class EmailSendingFailure extends RuntimeException {
    public EmailSendingFailure(String message) {
        super(message);
    }
}
