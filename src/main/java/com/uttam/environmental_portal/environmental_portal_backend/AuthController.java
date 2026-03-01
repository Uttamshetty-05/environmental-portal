package com.uttam.environmental_portal.environmental_portal_backend;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

@PostMapping("/register")
public ResponseEntity<?> register(@RequestBody User user) {

    if (userRepository.findByEmail(user.getEmail()).isPresent()) {
        return ResponseEntity.badRequest().body("Email already exists");
    }

    user.setPassword(passwordEncoder.encode(user.getPassword()));

    // Always force normal users to USER role
    user.setRole("ROLE_USER");

    userRepository.save(user);

    return ResponseEntity.ok("User registered successfully");
}

   @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody Map<String, String> request) {

    String email = request.get("email");
    String password = request.get("password");

    User user = userRepository.findByEmail(email).orElse(null);

    if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    String token = jwtUtil.generateToken(user.getEmail(), user.getRole());

    Map<String, String> response = new HashMap<>();
    response.put("token", token);

    return ResponseEntity.ok(response);
}
}