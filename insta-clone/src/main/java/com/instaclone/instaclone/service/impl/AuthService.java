package com.instaclone.instaclone.service.impl;

import com.instaclone.instaclone.dto.auth.LoginDto;
import com.instaclone.instaclone.exception.BadCredentialsException;
import com.instaclone.instaclone.security.JWTUserDetails;
import com.instaclone.instaclone.security.TokenUtils;
import com.instaclone.instaclone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenUtils tokenUtils;

    public String login(LoginDto login) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    login.getUsername(), login.getPassword()));
        } catch (Exception e) {
            throw new BadCredentialsException("Losi kredencijali!");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Map<String, Object> claims = new HashMap<>();
        var u = userService.findByUsername(login.getUsername());
        claims.put("role", u.getRole());
        var user = (JWTUserDetails) authentication.getPrincipal();

        return tokenUtils.generateToken(user.getUsername(), claims);
    }
}
