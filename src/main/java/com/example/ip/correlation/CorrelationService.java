package com.example.ip.correlation;

import com.example.ip.incidents.model.Incident;
import com.example.ip.incidents.model.IncidentStatus;
import com.example.ip.incidents.model.Severity;
import com.example.ip.incidents.repository.IncidentRepository;
import com.example.ip.ingestion.model.Event;
import com.example.ip.ingestion.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class CorrelationService {

    private final EventRepository eventRepo;
    private final IncidentRepository incidentRepo;

    public CorrelationService(
            EventRepository eventRepo,
            IncidentRepository incidentRepo
    ) {
        this.eventRepo = eventRepo;
        this.incidentRepo = incidentRepo;
    }

    public void correlate() {
        List<Event> recent =
                eventRepo.findByTimestampAfter(
                        Instant.now().minusSeconds(300)
                );

        if (recent.size() > 5) {
            Incident incident = new Incident();
            incident.setStatus(IncidentStatus.OPEN);
            incident.setSeverity(Severity.HIGH);
            incident.setOpenedAt(Instant.now());
            incidentRepo.save(incident);
        }
    }
}

