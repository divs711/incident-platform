package com.example.ip.incidents.controller;

import com.example.ip.incidents.dto.IncidentAnalysisResponse;
import com.example.ip.incidents.dto.IncidentResponse;
import com.example.ip.incidents.service.IncidentAnalysisService;
import com.example.ip.incidents.service.IncidentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/incidents")
@AllArgsConstructor
public class IncidentController {

    private final IncidentService service;

    private final IncidentAnalysisService analysisService;


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

    @GetMapping("/{id}/analysis")
    public ResponseEntity<IncidentAnalysisResponse> getAnalysis(
            @PathVariable Long id
    ) {
        return analysisService.getAnalysis(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }
}

