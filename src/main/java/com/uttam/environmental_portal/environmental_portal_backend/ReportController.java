package com.uttam.environmental_portal.environmental_portal_backend;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/submit")
    public ResponseEntity<?> submitReport(@Valid @RequestBody Report report, Authentication auth) {
        String email = auth.getName();
        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            return ResponseEntity.status(401).body("User not found");
        }

        report.setUser(user);
        report.setSubmittedAt(LocalDateTime.now());

        reportRepository.save(report);

        return ResponseEntity.ok("Report submitted successfully");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Report>> getAllReports(Authentication auth) {
        String email = auth.getName();
        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            return ResponseEntity.status(401).build();
        }

        List<Report> reports = reportRepository.findAll();
        return ResponseEntity.ok(reports);
    }
}
