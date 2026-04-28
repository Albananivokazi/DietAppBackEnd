package com.diaetologie.diaetologenbe.controller;

import com.diaetologie.diaetologenbe.entity.Monitoring;
import com.diaetologie.diaetologenbe.entity.Patientenfall;
import com.diaetologie.diaetologenbe.respository.MonitoringRepository;
import com.diaetologie.diaetologenbe.respository.PatientenfallRepository;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/monitoring")
public class MonitoringController {
    private final MonitoringRepository repo;
    private final PatientenfallRepository fallRepo;

    public MonitoringController(MonitoringRepository repo, PatientenfallRepository fallRepo) {
        this.repo = repo;
        this.fallRepo = fallRepo;
    }

    @GetMapping("/fall/{fallId}")
    public List<Monitoring> perFall(@PathVariable UUID fallId) {
        return repo.findByFallIdOrderByDatumDesc(fallId);
    }

    @GetMapping("/fall/{fallId}/faellig")
    public Map<String, Object> isFaellig(@PathVariable UUID fallId) {
        List<Monitoring> monitoringList = repo.findByFallIdOrderByDatumDesc(fallId);

        Map<String, Object> response = new HashMap<>();
        response.put("fallId", fallId.toString());

        int intervalDays = 14; // Monitoring alle 14 Tage

        if (monitoringList.isEmpty()) {
            response.put("faellig", true);
            response.put("message", "Kein Monitoring vorhanden. Bitte erstes Monitoring durchführen.");
            response.put("letzteMessung", null);
            response.put("naechstesDatum", LocalDate.now().toString());
        } else {
            LocalDate lastDate = monitoringList.get(0).getDatum();
            LocalDate nextDate = lastDate.plusDays(intervalDays);
            LocalDate today = LocalDate.now();

            boolean faellig = !nextDate.isAfter(today);

            response.put("faellig", faellig);
            response.put("letzteMessung", lastDate.toString());
            response.put("naechstesDatum", nextDate.toString());

            if (faellig) {
                response.put("message", "Monitoring ist fällig!");
            } else {
                response.put("message", "Monitoring noch nicht fällig");
            }
        }

        response.put("intervall", intervalDays);
        return response;
    }

    @PostMapping
    public Monitoring speichern(@RequestBody Map<String, Object> body) {
        UUID fallId = UUID.fromString((String) body.get("fallId"));
        Patientenfall fall = fallRepo.findById(fallId).orElseThrow();

        Monitoring m = new Monitoring();
        m.setFall(fall);

        if (body.get("datum") != null)
            m.setDatum(LocalDate.parse((String) body.get("datum")));
        if (body.get("gewicht") != null)
            m.setGewicht(new BigDecimal(body.get("gewicht").toString()));
        if (body.get("fettmasse") != null)
            m.setFettmasse(new BigDecimal(body.get("fettmasse").toString()));
        if (body.get("kalorienaufnahme") != null)
            m.setKalorienaufnahme(new BigDecimal(body.get("kalorienaufnahme").toString()));
        if (body.get("fettaufnahme") != null)
            m.setFettaufnahme(new BigDecimal(body.get("fettaufnahme").toString()));

        m.setZielerreichung((String) body.get("zielerreichung"));
        m.setFoerderndefaktoren((String) body.get("foerderndefaktoren"));
        m.setHemmendefaktoren((String) body.get("hemmendefaktoren"));
        m.setNotizen((String) body.get("notizen"));

        return repo.save(m);
    }
}