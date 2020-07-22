package com.server.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.server.user.UserEntity;
import com.server.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static com.server.auth.SecurityConstants.TOKEN_PREFIX;
import static com.server.auth.SecurityConstants.HEADER_STRING;
import static com.server.auth.SecurityConstants.SECRET;

@Component
public class UserComponent {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;

    public UserEntity getCurrentUser() {
        String jwtHeader = request.getHeader(HEADER_STRING);
        String token = jwtHeader.replace(TOKEN_PREFIX, "").trim();

        String username = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                .build()
                .verify(token.replace(TOKEN_PREFIX, ""))
                .getSubject();

        return userService.getUser(username);

    }

}
