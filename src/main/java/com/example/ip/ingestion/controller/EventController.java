package com.example.ip.ingestion.controller;

import com.example.ip.ingestion.dto.EventRequest;
import com.example.ip.ingestion.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/events")
@AllArgsConstructor
public class EventController {

    private final EventService service;

    @PostMapping
    public ResponseEntity<Void> ingest(
            @RequestHeader("Idempotency-Key") String key,
            @RequestBody EventRequest request
    ) {
        service.ingest(request, key);
        return ResponseEntity.accepted().build();
    }
}

