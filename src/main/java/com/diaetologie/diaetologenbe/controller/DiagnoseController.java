package com.diaetologie.diaetologenbe.controller;

import com.diaetologie.diaetologenbe.entity.Diagnose;
import com.diaetologie.diaetologenbe.entity.Patientenfall;
import com.diaetologie.diaetologenbe.respository.DiagnoseRepository;
import com.diaetologie.diaetologenbe.respository.PatientenfallRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/diagnosen")
public class DiagnoseController {

    private final DiagnoseRepository repo;
    private final PatientenfallRepository fallRepo;

    public DiagnoseController(DiagnoseRepository repo, PatientenfallRepository fallRepo) {
        this.repo = repo;
        this.fallRepo = fallRepo;
    }

    @GetMapping("/fall/{fallId}")
    public List<Diagnose> perFall(@PathVariable UUID fallId) {
        return repo.findByFallId(fallId);
    }

    @PostMapping
    public ResponseEntity<?> speichern(@RequestBody Map<String, Object> body) {
        try {
            String fallIdStr = (String) body.get("fallId");
            UUID fallId = UUID.fromString(fallIdStr);
            Patientenfall fall = fallRepo.findById(fallId).orElseThrow();

            Diagnose d = new Diagnose();
            d.setFall(fall);
            d.setProblem((String) body.get("problem"));
            d.setUrsache((String) body.get("ursache"));
            d.setSymptom((String) body.get("symptom"));
            d.setFoerderfaktoren((String) body.get("foerderfaktoren"));
            d.setBarrieren((String) body.get("barrieren"));
            d.setPrioritaet(body.get("prioritaet") != null ? ((Number) body.get("prioritaet")).intValue() : 1);

            return ResponseEntity.ok(repo.save(d));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Fehler: " + e.getMessage());
        }
    }
    @GetMapping("/fall/{fallId}/latest")
    public Diagnose getLatestByFallId(@PathVariable UUID fallId) {
        List<Diagnose> list = repo.findByFallId(fallId);
        return list.isEmpty() ? null : list.get(0);
    }
}