package com.example.ip.correlation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CorrelationWorker {

    private final CorrelationService service;

    public CorrelationWorker(CorrelationService service) {
        this.service = service;
    }

    @Scheduled(fixedDelay = 5000)
    public void run() {
        log.info("Running correlation service");
        service.correlate();
    }
}

