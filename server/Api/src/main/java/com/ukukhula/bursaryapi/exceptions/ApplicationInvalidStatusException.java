package com.ukukhula.bursaryapi.exceptions;

public class ApplicationInvalidStatusException extends RuntimeException {
    public ApplicationInvalidStatusException() {
        super("Invalid status value");
    }
}
