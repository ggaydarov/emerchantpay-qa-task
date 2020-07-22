package com.server.auth;

public class SecurityConstants {
    public static final String SECRET = "secretTemporaryStoredHere";
    public static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 10;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}