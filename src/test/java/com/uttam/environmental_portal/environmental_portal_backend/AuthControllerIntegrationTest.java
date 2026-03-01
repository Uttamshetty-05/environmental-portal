package com.uttam.environmental_portal.environmental_portal_backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")

public class AuthControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // Updated RegisterRequest class to include username
    static class RegisterRequest {
        public String email;
        public String password;
        public String username;  // Added username field

        public RegisterRequest(String email, String password, String username) {
            this.email = email;
            this.password = password;
            this.username = username;  // Initialize username
        }
    }

    static class LoginRequest {
        public String email;
        public String password;

        public LoginRequest(String email, String password) {
            this.email = email;
            this.password = password;
        }
    }

    @Test
    public void testUserRegistrationAndLogin() throws Exception {
        String testEmail = "testuser@example.com";
        String testPassword = "strongpassword";
        String testUsername = "testuser";  // Provide a username

        // Register the user with username included
        RegisterRequest registerRequest = new RegisterRequest(testEmail, testPassword, testUsername);
        mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isOk());

        // Login with same credentials
        LoginRequest loginRequest = new LoginRequest(testEmail, testPassword);
        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk());
    }
}
