package com.server.exceptions;

public class MissingUserException extends RuntimeException {

    private final String username;


    public MissingUserException(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
