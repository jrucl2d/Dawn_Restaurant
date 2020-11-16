package com.dawn.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.dawn.config.JWtProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger logger= LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        LoginViewModel credentials = null;
        try {
            credentials = new ObjectMapper().readValue(request.getInputStream(), LoginViewModel.class);
            System.out.println(credentials);
        } catch (IOException e) {
            logger.error("failed to mapping credential object");
            e.printStackTrace();
        }

        System.out.println("크리덴셜 = " + credentials.getUsername());
        System.out.println("크리덴셜 = " + credentials.getPassword());

        UsernamePasswordAuthenticationToken token
                = new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword(), new ArrayList<>());

        return authenticationManager.authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
        throws IOException, ServletException {
        System.out.println("인증 성공!");
        MainUserDetails principal = (MainUserDetails) authResult.getPrincipal();
        String token =
                JWT.create()
                   .withSubject(principal.getUsername())
                   .withExpiresAt(new Date(System.currentTimeMillis() + JWtProperties.EXPIRATION_TIME))
                   .sign(Algorithm.HMAC512(JWtProperties.SECRET.getBytes()));
        response.addHeader(JWtProperties.HEADER_STRING, JWtProperties.TOKEN_PREFIX + token);
    }
}
