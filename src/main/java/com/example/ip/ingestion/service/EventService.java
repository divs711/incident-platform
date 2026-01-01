package com.example.ip.ingestion.service;

import com.example.ip.ingestion.dto.EventRequest;
import com.example.ip.ingestion.model.Event;
import com.example.ip.ingestion.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class EventService {

    private final EventRepository repo;

    public EventService(EventRepository repo) {
        this.repo = repo;
    }

    public void ingest(EventRequest request, String idempotencyKey) {
        Event event = new Event();
        event.setService(request.service);
        event.setType(request.type);
        event.setMessage(request.message);
        event.setTimestamp(Instant.now());
        event.setIdempotencyKey(idempotencyKey);

        repo.save(event);
    }
}

