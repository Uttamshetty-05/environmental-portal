package com.uttam.environmental_portal.environmental_portal_backend;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/env-data")
public class EnvironmentalDataController {

    @Autowired
    private EnvironmentalDataRepository repository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/submit")
    public String submitData(@RequestBody EnvironmentalData data, Principal principal) {

        if (principal == null) {
            return "User not authenticated";
        }

        User user = userRepository.findByEmail(principal.getName()).orElse(null);

        if (user == null) {
            return "User not found";
        }

        data.setUser(user);

        if (data.getTimestamp() == null) {
            data.setTimestamp(LocalDateTime.now());
        }

        repository.save(data);

        return "Data saved successfully";
    }

    @GetMapping("/my-data")
    public List<EnvironmentalData> getMyData(Principal principal) {

        if (principal == null) {
            return List.of();
        }

        User user = userRepository.findByEmail(principal.getName()).orElse(null);

        if (user == null) {
            return List.of();
        }

        return repository.findByUser(user);
    }
}