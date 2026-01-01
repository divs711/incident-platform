package com.example.ip.ai;

import com.example.ip.ai.model.AiInsight;
import com.example.ip.incidents.model.Incident;

public interface AiAnalyzer {
    AiInsight analyze(Incident incident);
}

