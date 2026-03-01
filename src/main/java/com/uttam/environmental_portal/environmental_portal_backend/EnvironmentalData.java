package com.uttam.environmental_portal.environmental_portal_backend;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "environmental_data")
public class EnvironmentalData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Data Type is required")
    @Column(name = "data_type", nullable = false)
    private String dataType;

    @NotBlank(message = "Location is required")
    @Column(nullable = false)
    private String location;

    @NotBlank(message = "Parameter is required")
    @Column(nullable = false)
    private String parameter;

    // 🔥 FIXED (value is reserved word in H2)
    @NotNull(message = "Value is required")
    @Column(name = "measurement_value", nullable = false)
    private Double value;

    @NotBlank(message = "Unit is required")
    @Column(nullable = false)
    private String unit;

    @Column(length = 500)
    private String description;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    // 🔥 Relationship with User
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public EnvironmentalData() {}

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}