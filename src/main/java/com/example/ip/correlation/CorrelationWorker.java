package com.example.ip.correlation;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CorrelationWorker {

    private final CorrelationService service;

    public CorrelationWorker(CorrelationService service) {
        this.service = service;
    }

    @Scheduled(fixedDelay = 5000)
    public void run() {
        service.correlate();
    }
}

