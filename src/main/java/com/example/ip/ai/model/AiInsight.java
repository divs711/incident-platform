package com.example.ip.ai.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
public class AiInsight {

    @Id
    @GeneratedValue
    private Long id;

    private Long incidentId;

    @Column(length = 2000)
    private String summary;

    private double confidence;

    private Instant createdAt;
}


