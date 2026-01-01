package com.example.ip.incidents.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Incident {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private IncidentStatus status;

    @Enumerated(EnumType.STRING)
    private Severity severity;

    private Instant openedAt;
}

