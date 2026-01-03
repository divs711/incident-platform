package com.example.ip.ai.repository;

import com.example.ip.ai.model.AiInsight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AiInsightRepository  extends JpaRepository<AiInsight, Long> {
    Optional<AiInsight> findByIncidentId(Long incidentId);
}
