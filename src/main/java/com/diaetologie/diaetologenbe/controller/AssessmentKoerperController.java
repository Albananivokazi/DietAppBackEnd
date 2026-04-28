package com.diaetologie.diaetologenbe.controller;

import com.diaetologie.diaetologenbe.entity.AssessmentKoerper;
import com.diaetologie.diaetologenbe.entity.Patientenfall;
import com.diaetologie.diaetologenbe.respository.AssessmentKoerperRepository;
import com.diaetologie.diaetologenbe.respository.PatientenfallRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/assessment/koerper")
public class AssessmentKoerperController {

    private final AssessmentKoerperRepository repo;
    private final PatientenfallRepository fallRepo;

    public AssessmentKoerperController(AssessmentKoerperRepository repo,
                                       PatientenfallRepository fallRepo) {
        this.repo = repo;
        this.fallRepo = fallRepo;
    }

    @GetMapping("/fall/{fallId}")
    public List<AssessmentKoerper> perFall(@PathVariable UUID fallId) {
        return repo.findByFallId(fallId);
    }

    @PostMapping
    public ResponseEntity<?> speichern(@RequestBody Map<String, Object> body) {
        try {
            // fall kommt als { id: "uuid" }
            Map<String, Object> fallMap = (Map<String, Object>) body.get("fall");
            UUID fallId = UUID.fromString((String) fallMap.get("id"));
            Patientenfall fall = fallRepo.findById(fallId).orElseThrow();

            AssessmentKoerper k = new AssessmentKoerper();
            k.setFall(fall);
            k.setGroesse(toBD(body.get("groesse")));
            k.setGewicht(toBD(body.get("gewicht")));
            k.setBmi(toBD(body.get("bmi")));
            k.setBmiPerzentile((String) body.get("bmiPerzentile"));
            k.setBlutdruckSystolisch(toInt(body.get("blutdruckSystolisch")));
            k.setBlutdruckDiastolisch(toInt(body.get("blutdruckDiastolisch")));
            k.setFettmasse(toBD(body.get("fettmasse")));
            k.setFettmasseIdealMin(toBD(body.get("fettmasseIdealMin")));
            k.setFettmasseIdealMax(toBD(body.get("fettmasseIdealMax")));
            k.setMuskelmasse(toBD(body.get("muskelmasse")));
            k.setMuskelmasseHoch(body.get("muskelmasseHoch") != null && (Boolean) body.get("muskelmasseHoch"));
            k.setPhasenwinkel(toBD(body.get("phasenwinkel")));
            k.setKoerperzellmasse(toBD(body.get("koerperzellmasse")));
            k.setKoerperzellmasseIdealMin(toBD(body.get("koerperzellmasseIdealMin")));
            k.setKoerperzellmasseIdealMax(toBD(body.get("koerperzellmasseIdealMax")));
            k.setLaborparameterStatus((String) body.get("laborparameterStatus"));

            return ResponseEntity.ok(repo.save(k));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Fehler: " + e.getMessage());
        }
    }

    private BigDecimal toBD(Object v) {
        if (v == null) return null;
        return new BigDecimal(v.toString());
    }
    private Integer toInt(Object v) {
        if (v == null) return null;
        return ((Number) v).intValue();
    }

    @GetMapping("/fall/{fallId}/latest")
    public AssessmentKoerper getLatestByFallId(@PathVariable UUID fallId) {
        List<AssessmentKoerper> list = repo.findByFallId(fallId);
        return list.isEmpty() ? null : list.get(0);
    }
}