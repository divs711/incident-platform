package com.example.ip.ingestion.repository;

import com.example.ip.ingestion.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Repository
public interface EventRepository
        extends JpaRepository<Event, UUID> {

    List<Event> findByTimestampAfter(Instant after);
}

