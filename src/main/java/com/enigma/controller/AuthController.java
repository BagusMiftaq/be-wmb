package com.enigma.controller;

import com.enigma.model.Auth;
import com.enigma.model.response.SuccessResponse;
import com.enigma.service.AuthService;
import com.enigma.utils.UrlMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UrlMapping.AUTH)
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(UrlMapping.LOGIN)
    public ResponseEntity login(@RequestBody Auth login){
        String token = authService.login(login);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success login", token));
    }

}
