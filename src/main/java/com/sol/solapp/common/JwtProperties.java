package com.sol.solapp.common;

public interface JwtProperties {
    String TOKEN_PREFIX = "Bearer ";
    String AUTH_HEADER = "Authorization";
    int EXPIRE_TIME = 864000000;
}
