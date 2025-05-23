package com.example.organization.exception;

public class OrganizationAlreadyExistsException extends RuntimeException {

    public OrganizationAlreadyExistsException(String message) {
        super(message);
    }
}
