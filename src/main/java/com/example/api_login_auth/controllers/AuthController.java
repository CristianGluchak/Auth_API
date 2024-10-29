package com.example.api_login_auth.controllers;

import com.example.api_login_auth.Infra.security.TokenService;
import com.example.api_login_auth.domain.user.User;
import com.example.api_login_auth.dtos.LoginRequestDTO;
import com.example.api_login_auth.dtos.LoginResponseDTO;
import com.example.api_login_auth.dtos.RegisterRequestDTO;
import com.example.api_login_auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 27/10/2024
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body) {
        User user = userRepository.findByEmail(body.getEmail()).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
        if (passwordEncoder.matches(body.getPassword(),user.getPassword())) {
            String token = tokenService.generateToken(user);
            return ResponseEntity.ok(LoginResponseDTO.builder()
                    .name(user.getName())
                    .token(token)
                    .build());
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO body) {
        Optional<User> user = userRepository.findByEmail(body.getEmail());
        if (user.isEmpty()) {
            User newUser = new User();
            newUser.setName(body.getName());
            newUser.setEmail(body.getEmail());
            newUser.setPassword(passwordEncoder.encode(body.getPassword()));
            userRepository.save(newUser);
            String token = tokenService.generateToken(newUser);
            return ResponseEntity.ok(LoginResponseDTO.builder()
                    .name(newUser.getName())
                    .token(token)
                    .build());

        }
        return ResponseEntity.badRequest().build();
    }
}
