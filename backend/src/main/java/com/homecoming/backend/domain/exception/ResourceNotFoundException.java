package com.homecoming.backend.domain.exception;

public final class ResourceNotFoundException extends DomainException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
