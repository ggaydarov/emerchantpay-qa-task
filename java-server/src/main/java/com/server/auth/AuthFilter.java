package com.server.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static com.server.auth.SecurityConstants.HEADER_STRING;
import static com.server.auth.SecurityConstants.TOKEN_PREFIX;
import static com.server.auth.SecurityConstants.SECRET;

public class AuthFilter extends BasicAuthenticationFilter {
    private static final Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    public AuthFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);
        Cookie[] cookies = req.getCookies();

        if(header != null && header.startsWith(TOKEN_PREFIX)) {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(req, res);
            return;
        }

        if (header == null && cookies == null) {
            chain.doFilter(req, res);
            return;
        }

        for (Cookie ck : cookies) {
            if ("X-TOKEN".equals(ck.getName())) {
                UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                chain.doFilter(req, res);
                return;
            }
        }

        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            String user = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""))
                    .getSubject();

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }
            return null;
        } else {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie ck : cookies) {
                    if ("X-TOKEN".equals(ck.getName())) {
                        token = ck.getValue();
                        String user = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                                .build()
                                .verify(token)
                                .getSubject();

                        if (user != null) {
                            return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
                        }
                    }
                }
                return null;
            }
        }
        return null;
    }
}