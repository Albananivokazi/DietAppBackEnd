package com.diaetologie.diaetologenbe.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.*;

@Service
public class GroqService {

    @Value("${groq.api.key:}")
    private String apiKey;

    @Value("${groq.model:mixtral-8x7b-32768}")
    private String modelName;

    private final RestTemplate restTemplate;

    public GroqService() {
        this.restTemplate = new RestTemplate();
    }

    public String ernaehrungsempfehlung(
            Double bmi, Double fettmasse, Double fettmasseIdealMax,
            Integer blutdruckSys, Integer blutdruckDia,
            Double energiebedarfKcal, Double kalorienaufnahme,
            Double fettbedarfG, Double fettaufnahme,
            String zuckergetraenke, String alkoholKonsum,
            String fertigprodukteKonsum, String suesswarenKonsum,
            Boolean kochbereitschaft,
            String motivationLevel,
            String problem, String ursache, String symptom) {

        System.out.println("Groq API Key: " + (apiKey != null ? apiKey.substring(0, 20) + "..." : "NULL"));
        System.out.println("Groq Model: " + modelName);

        if (apiKey == null || apiKey.isEmpty()) {
            System.out.println("apiKey is null or empty");
            return getMockResponse();
        }

        try {
            String prompt = buildPrompt(bmi, fettmasse, fettmasseIdealMax,
                    blutdruckSys, blutdruckDia, energiebedarfKcal, kalorienaufnahme,
                    fettbedarfG, fettaufnahme, zuckergetraenke, alkoholKonsum,
                    fertigprodukteKonsum, suesswarenKonsum, kochbereitschaft,
                    motivationLevel, problem, ursache, symptom);

            String url = "https://api.groq.com/openai/v1/chat/completions";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + apiKey);

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("model", modelName);
            requestBody.put("temperature", 0.7);
            requestBody.put("max_tokens", 1500);

            List<Map<String, String>> messages = new ArrayList<>();

            Map<String, String> systemMessage = new HashMap<>();
            systemMessage.put("role", "system");
            systemMessage.put("content", "Du bist ein erfahrener Diaetologe. Erstelle personalisierte Ernährungsempfehlungen auf Deutsch.");
            messages.add(systemMessage);

            Map<String, String> userMessage = new HashMap<>();
            userMessage.put("role", "user");
            userMessage.put("content", prompt);
            messages.add(userMessage);

            requestBody.put("messages", messages);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
            ResponseEntity<Map> response = restTemplate.postForEntity(url, entity, Map.class);

            if (response.getBody() != null) {
                List<Map<String, Object>> choices = (List<Map<String, Object>>) response.getBody().get("choices");
                if (choices != null && !choices.isEmpty()) {
                    Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
                    return (String) message.get("content");
                }
            }

            return getMockResponse();

        } catch (Exception e) {
            System.err.println("Groq API error: " + e.getMessage());
            e.printStackTrace();
            return getMockResponse();
        }
    }

    private String buildPrompt(
            Double bmi, Double fettmasse, Double fettmasseIdealMax,
            Integer blutdruckSys, Integer blutdruckDia,
            Double energiebedarfKcal, Double kalorienaufnahme,
            Double fettbedarfG, Double fettaufnahme,
            String zuckergetraenke, String alkohol,
            String fertig, String suess,
            Boolean kocht, String motivation,
            String problem, String ursache, String symptom) {

        return String.format("""
            Du bist ein erfahrener Diaetologe. Erstelle eine personalisierte 
            Ernaehrungsempfehlung basierend auf diesen klinischen Daten.
            
            KLINISCHE DATEN:
            - BMI: %.1f kg/m²
            - Fettmasse: %.1f kg (Ziel: unter %.1f kg)
            - Blutdruck: %d/%d mmHg
            - Energiebedarf: %.0f kcal/Tag | Aktuelle Aufnahme: %.0f kcal/Tag
            - Fettbedarf: %.0f g/Tag | Aktuelle Fettaufnahme: %.0f g/Tag
            - Zuckergetraenke/Energydrinks: %s
            - Alkohol: %s
            - Fertigprodukte: %s
            - Suesswaren: %s
            - Kochbereitschaft: %s
            - Motivation: %s
            
            DIAETOLOGISCHE DIAGNOSE:
            - Problem: %s
            - Ursache: %s
            - Symptome: %s
            
            Bitte erstelle auf Deutsch eine strukturierte Antwort mit:
            1. 📋 Kurze Analyse der Situation (2-3 Sätze)
            2. 🎯 3 konkrete Sofortmassnahmen
            3. 🍽️ Taegliche Ernährungsempfehlungen
            4. 📊 Langfristige Ziele (3 Monate)
            5. ⚠️ Was vermieden werden sollte
            """,
                safe(bmi), safe(fettmasse), safe(fettmasseIdealMax),
                safeInt(blutdruckSys), safeInt(blutdruckDia),
                safe(energiebedarfKcal), safe(kalorienaufnahme),
                safe(fettbedarfG), safe(fettaufnahme),
                safeStr(zuckergetraenke), safeStr(alkohol),
                safeStr(fertig), safeStr(suess),
                Boolean.TRUE.equals(kocht) ? "Ja" : "Nein",
                safeStr(motivation),
                safeStr(problem), safeStr(ursache), safeStr(symptom)
        );
    }

    private String getMockResponse() {
        return """
            📋 **KI-Analyse. Moc up
            """;
    }

    private double safe(Double v) { return v != null ? v : 0.0; }
    private int safeInt(Integer v) { return v != null ? v : 0; }
    private String safeStr(String v) { return v != null && !v.isEmpty() ? v : "nicht angegeben"; }
}