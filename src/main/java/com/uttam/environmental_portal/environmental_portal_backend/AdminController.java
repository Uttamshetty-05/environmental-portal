package com.uttam.environmental_portal.environmental_portal_backend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private EnvironmentalDataRepository environmentalDataRepository;

    @Autowired
    private UserRepository userRepository;

    // 🔥 Only ADMIN can access
    @GetMapping("/all-data")
    @PreAuthorize("hasRole('ADMIN')")
    public List<EnvironmentalData> getAllEnvironmentalData() {
        return environmentalDataRepository.findAll();
    }

    // 🔥 Only ADMIN can access
    @GetMapping("/all-users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}