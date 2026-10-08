package com.P1.Gaser.Controllers;

import com.P1.Gaser.DTO.LoginRequest;
import com.P1.Gaser.DTO.LoginResponse;
import com.P1.Gaser.Services.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponse login(
            @Valid @RequestBody LoginRequest request) {

        return authService.login(request);
    }
}