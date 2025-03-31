package com.backend.memora.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.backend.memora.dto.LoginRequest;
import com.backend.memora.model.User;
import com.backend.memora.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService; // ✅ Inject JwtService
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // ✅ User Registration
    public String registerUser(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            return "Email already registered!";
        }

        // Encrypt password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully!";
    }

    // ✅ User Login
    public ResponseEntity<?> loginUser(LoginRequest loginRequest) {
        Optional<User> userOptional = userRepository.findByEmail(loginRequest.getEmail());

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password!");
        }

        User user = userOptional.get();
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password!");
        }

        // ✅ Generate JWT Token
        String token = jwtService.generateToken(user);

        // ✅ Return token in response
        return ResponseEntity.ok(Map.of("token", token));
    }
}
