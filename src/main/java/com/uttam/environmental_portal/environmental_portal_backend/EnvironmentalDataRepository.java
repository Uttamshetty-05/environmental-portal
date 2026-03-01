package com.uttam.environmental_portal.environmental_portal_backend;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EnvironmentalDataRepository extends JpaRepository<EnvironmentalData, Long> {

    List<EnvironmentalData> findByUser(User user);
}