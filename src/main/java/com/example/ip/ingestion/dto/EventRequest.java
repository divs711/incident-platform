package com.example.ip.ingestion.dto;

import com.example.ip.ingestion.model.EventType;
import lombok.Data;

@Data
public class EventRequest {

    public String service;
    public EventType type;
    public String message;
}

