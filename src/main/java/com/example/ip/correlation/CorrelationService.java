package com.example.ip.correlation;

import com.example.ip.incidents.model.Incident;
import com.example.ip.incidents.model.IncidentStatus;
import com.example.ip.incidents.model.Severity;
import com.example.ip.incidents.repository.IncidentRepository;
import com.example.ip.ingestion.model.Event;
import com.example.ip.ingestion.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CorrelationService {

    private final EventRepository eventRepo;
    private final IncidentRepository incidentRepo;


    public void correlate() {
        List<Event> recent = eventRepo.findByTimestampAfter(
                        Instant.now().minusSeconds(300)
                );

        Map<String, List<Event>> byService =
                recent.stream()
                        .collect(Collectors.groupingBy(Event::getService));

        for (var entry : byService.entrySet()) {
            String service = entry.getKey();
            List<Event> events = entry.getValue();

            if (events.size() < 5) continue;

            boolean exists = incidentRepo.existsByServiceAndStatus(
                            service,
                            IncidentStatus.OPEN);

            if (exists) continue;

            Incident incident = new Incident();
            incident.setService(service);
            incident.setStatus(IncidentStatus.OPEN);
            incident.setSeverity(Severity.HIGH);
            incident.setOpenedAt(Instant.now());

            incidentRepo.save(incident);
        }
    }
}

