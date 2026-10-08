package com.P1.Gaser.Services;

import com.P1.Gaser.DTO.LoginRequest;
import com.P1.Gaser.DTO.LoginResponse;
import com.P1.Gaser.Entity.User;
import com.P1.Gaser.Exception.InvalidCredentialsException;
import com.P1.Gaser.Repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponse login(LoginRequest request) {

        String login = request.getLogin();

        User user;

        if (login.contains("@")) {

            user = userRepository.findByEmail(login)
                    .orElseThrow(() ->
                            new InvalidCredentialsException(
                                    "Invalid login credentials"));
        } else {

            user = userRepository.findByPhoneNumber(login)
                    .orElseThrow(() ->
                            new InvalidCredentialsException(
                                    "Invalid login credentials"));
        }

        boolean passwordMatches =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword()
                );

        if (!passwordMatches) {
            throw new InvalidCredentialsException(
                    "Invalid login credentials");
        }

        String token = jwtService.generateToken(user);

        return new LoginResponse(
                token,
                user.getUserId(),
                user.getRole().name()
        );
    }
}