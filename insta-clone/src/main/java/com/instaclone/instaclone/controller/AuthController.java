package com.instaclone.instaclone.controller;

import com.instaclone.instaclone.dto.auth.LoginDto;
import com.instaclone.instaclone.dto.auth.RegistrationDto;
import com.instaclone.instaclone.dto.auth.Token;
import com.instaclone.instaclone.service.UserService;
import com.instaclone.instaclone.service.impl.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;
//    private final KieSession kieSession;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Token login(@RequestBody @Validated LoginDto login) {
        return new Token(authService.login(login));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/registration")
    public Boolean registration(@Validated @RequestBody RegistrationDto registrationDto) {
        userService.registration(registrationDto);
        return true;
    }

//    @PostMapping
//    public Integer testDrools(@RequestBody Product product){
//        kieSession.insert(product);
//        kieSession.fireAllRules();
//        return product.getDiscount();
//    }

}
