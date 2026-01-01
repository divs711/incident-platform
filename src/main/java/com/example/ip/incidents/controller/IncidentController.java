package com.example.ip.incidents.controller;

import com.example.ip.incidents.dto.IncidentResponse;
import com.example.ip.incidents.service.IncidentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/incidents")
@AllArgsConstructor
public class IncidentController {

    private final IncidentService service;


    @GetMapping
    public List<IncidentResponse> list() {
        return service.getOpenIncidents()
                .stream()
                .map(IncidentResponse::from)
                .toList();
    }

    @PostMapping("/{id}/resolve")
    public void resolve(@PathVariable Long id) {
        service.resolve(id);
    }
}

