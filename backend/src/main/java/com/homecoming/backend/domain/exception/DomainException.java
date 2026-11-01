package com.homecoming.backend.domain.exception;

public sealed class DomainException extends RuntimeException permits ResourceNotFoundException {

    protected DomainException(String message) {
        super(message);
    }
}
