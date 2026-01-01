package com.example.ip.incidents.repository;

import com.example.ip.incidents.model.Incident;
import com.example.ip.incidents.model.IncidentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidentRepository
        extends JpaRepository<Incident, Long> {

    List<Incident> findByStatus(IncidentStatus status);
}

