package com.example.ip.incidents.dto;

import com.example.ip.incidents.model.Incident;
import com.example.ip.incidents.model.IncidentStatus;
import com.example.ip.incidents.model.Severity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IncidentResponse {

    public Long id;
    public Severity severity;
    public IncidentStatus status;

    public static IncidentResponse from(Incident i) {
        IncidentResponse r = new IncidentResponse();
        r.id = i.getId();
        r.severity = i.getSeverity();
        r.status = i.getStatus();
        return r;
    }
}

