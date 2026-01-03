package com.example.ip.ai;

import com.example.ip.ai.model.AiInsight;
import com.example.ip.incidents.model.Incident;
import org.springframework.stereotype.Component;

@Component
public class GeminiAnalyzer implements AiAnalyzer {

    @Override
    public AiInsight analyze(Incident incident) {
//        return new AiInsight(
//                "Likely spike in error rate",
//                0.7
//        );
        return null;
    }
}
