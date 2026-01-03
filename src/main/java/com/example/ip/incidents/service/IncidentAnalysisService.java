package com.example.ip.incidents.service;

import com.example.ip.ai.repository.AiInsightRepository;
import com.example.ip.incidents.dto.IncidentAnalysisResponse;
import com.example.ip.incidents.repository.IncidentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IncidentAnalysisService {

    private final AiInsightRepository insightRepo;
    private final IncidentRepository incidentRepo;

    public IncidentAnalysisService(
            AiInsightRepository insightRepo,
            IncidentRepository incidentRepo
    ) {
        this.insightRepo = insightRepo;
        this.incidentRepo = incidentRepo;
    }

    public Optional<IncidentAnalysisResponse> getAnalysis(Long incidentId) {
        // Ensure incident exists (important!)
        incidentRepo.findById(incidentId)
                .orElseThrow(() -> new IllegalArgumentException("Incident not found"));

        return insightRepo.findByIncidentId(incidentId)
                .map(IncidentAnalysisResponse::from);
    }
}

