package com.example.ip.ai;

import com.example.ip.ai.model.AiInsight;
import com.example.ip.ai.repository.AiInsightRepository;
import com.example.ip.incidents.model.Incident;
import com.example.ip.incidents.model.IncidentStatus;
import com.example.ip.incidents.repository.IncidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AiWorker {

    private final IncidentRepository incidentRepo;
    private final AiAnalyzer analyzer;
    private final AiInsightRepository insightRepo;

    @Scheduled(fixedDelay = 15000)
    public void analyze() {
        List<Incident> incidents =
                incidentRepo.findByStatus(IncidentStatus.OPEN);

        for (Incident incident : incidents) {
            if (insightRepo.findByIncidentId(incident.getId()).isPresent()) {
                continue; // already analyzed
            }

            AiInsight insight = analyzer.analyze(incident);
            insight.setIncidentId(incident.getId());
            insight.setCreatedAt(Instant.now());

            insightRepo.save(insight);
        }
    }
}

