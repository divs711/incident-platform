package com.example.ip.ai;

import com.example.ip.incidents.model.IncidentStatus;
import com.example.ip.incidents.repository.IncidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AiWorker {

    private final IncidentRepository repo;
    private final AiAnalyzer analyzer;


    @Scheduled(fixedDelay = 15000)
    public void analyze() {
        repo.findByStatus(IncidentStatus.OPEN)
                .forEach(analyzer::analyze);
    }
}

