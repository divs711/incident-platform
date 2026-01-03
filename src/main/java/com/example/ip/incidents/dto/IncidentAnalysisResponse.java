package com.example.ip.incidents.dto;

import com.example.ip.ai.model.AiInsight;
import lombok.Data;

import java.time.Instant;

@Data
public class IncidentAnalysisResponse {

    public Long incidentId;
    public String summary;
    public double confidence;
    public Instant createdAt;

    public static IncidentAnalysisResponse from(AiInsight insight) {
        IncidentAnalysisResponse r = new IncidentAnalysisResponse();
        r.incidentId = insight.getIncidentId();
        r.summary = insight.getSummary();
        r.confidence = insight.getConfidence();
        r.createdAt = insight.getCreatedAt();
        return r;
    }
}

