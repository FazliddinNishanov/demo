package com.example.demo.security;

/**
 * Developed by: Fazliddin Nishanov
 * Date: 21.06.2022
 */

public class SecurityConstans {

    public static final String SIGN_UP_URLS = "/api/auth/**";

    public static final String SECRET = "SecretKeyForJwt";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String CONTENT_TYPE = "application/json";
    public static final long EXPIRATION_TIME = 600_000; // 10 minut

}
