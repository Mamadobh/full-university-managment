package com.global.university.exception;

public class DataDuplicationException extends RuntimeException {
    public DataDuplicationException() {
    }

    public DataDuplicationException(String message) {
        super(message);
    }
}
