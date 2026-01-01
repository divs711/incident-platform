package com.example.ip.ingestion.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "events", indexes = {
        @Index(columnList = "service,timestamp")
})
@Data
public class Event {

    @Id
    @GeneratedValue
    private UUID id;

    private String service;

    @Enumerated(EnumType.STRING)
    private EventType type;

    private String message;

    private Instant timestamp;

    private String idempotencyKey;
}

