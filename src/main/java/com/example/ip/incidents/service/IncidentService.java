package com.example.ip.incidents.service;

import com.example.ip.incidents.model.Incident;
import com.example.ip.incidents.model.IncidentStatus;
import com.example.ip.incidents.repository.IncidentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidentService {

    private final IncidentRepository repo;

    public IncidentService(IncidentRepository repo) {
        this.repo = repo;
    }

    public List<Incident> getOpenIncidents() {
        return repo.findByStatus(IncidentStatus.OPEN);
    }

    public void resolve(Long id) {
        Incident incident = repo.findById(id).orElseThrow();
        incident.setStatus(IncidentStatus.RESOLVED);
        repo.save(incident);
    }
}

