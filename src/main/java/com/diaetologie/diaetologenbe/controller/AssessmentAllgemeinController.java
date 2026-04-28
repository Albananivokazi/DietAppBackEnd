package com.diaetologie.diaetologenbe.controller;

import com.diaetologie.diaetologenbe.entity.AssessmentAllgemein;
import com.diaetologie.diaetologenbe.entity.Patientenfall;
import com.diaetologie.diaetologenbe.respository.AssessmentAllgemeinRepository;
import com.diaetologie.diaetologenbe.respository.PatientenfallRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/assessment/allgemein")
public class AssessmentAllgemeinController {

    private final AssessmentAllgemeinRepository repo;
    private final PatientenfallRepository fallRepo;

    public AssessmentAllgemeinController(AssessmentAllgemeinRepository repo,
                                         PatientenfallRepository fallRepo) {
        this.repo = repo;
        this.fallRepo = fallRepo;
    }

    @GetMapping("/fall/{fallId}")
    public List<AssessmentAllgemein> perFall(@PathVariable UUID fallId) {
        return repo.findByFallId(fallId);
    }

    @PostMapping
    public ResponseEntity<?> speichern(@RequestBody Map<String, Object> body) {
        try {
            String fallIdStr = (String) body.get("fallId");
            if (fallIdStr == null) {
                return ResponseEntity.badRequest().body("fallId fehlt im Request");
            }
            UUID fallId = UUID.fromString(fallIdStr);
            Patientenfall fall = fallRepo.findById(fallId)
                    .orElseThrow(() -> new RuntimeException("Fall nicht gefunden: " + fallId));

            AssessmentAllgemein a = new AssessmentAllgemein();
            a.setFall(fall);
            a.setMuedigkeitLevel((String) body.get("muedigkeitLevel"));
            a.setKopfschmerzen(body.get("kopfschmerzen") != null && (Boolean) body.get("kopfschmerzen"));
            a.setWissenErnaehrungLevel((String) body.get("wissenErnaehrungLevel"));
            a.setMotivationLevel((String) body.get("motivationLevel"));
            a.setKlientenPerspektive((String) body.get("klientenPerspektive"));
            a.setNotizen((String) body.get("notizen"));

            return ResponseEntity.ok(repo.save(a));

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Fehler: " + e.getMessage());
        }
    }
    @GetMapping("/fall/{fallId}/latest")
    public AssessmentAllgemein getLatestByFallId(@PathVariable UUID fallId) {
        List<AssessmentAllgemein> list = repo.findByFallId(fallId);
        return list.isEmpty() ? null : list.get(0);
    }
}
