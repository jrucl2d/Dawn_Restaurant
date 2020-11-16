package com.dawn.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.dawn.config.JWtProperties;
import com.dawn.model.User;
import com.dawn.repository.user.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;


public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final UserRepository userRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    @Transactional
    protected Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {
        String token = request.getHeader(JWtProperties.HEADER_STRING);
        if (token != null) {
            String username =
                    JWT.require(Algorithm.HMAC512(JWtProperties.SECRET.getBytes()))
                            .build()
                            .verify(token.replace(JWtProperties.TOKEN_PREFIX, ""))
                            .getSubject();

            if (username != null) {
                String contextPath = request.getContextPath();
                String path = request.getRequestURI().toString();
                System.out.println(path);

                User user = userRepository.findUserByLoginId(username);
                MainUserDetails mainUserDetails = new MainUserDetails(user, userRepository);
                UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(username, null, mainUserDetails.getAuthorities());
                return auth;

            }
        }
        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        String header = request.getHeader(JWtProperties.HEADER_STRING);
        if(header == null || !header.startsWith(JWtProperties.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        Authentication authentication = getUsernamePasswordAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }
}
