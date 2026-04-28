package com.diaetologie.diaetologenbe.controller;

import com.diaetologie.diaetologenbe.entity.AssessmentErnaehrung;
import com.diaetologie.diaetologenbe.entity.Patientenfall;
import com.diaetologie.diaetologenbe.respository.AssessmentErnaehrungRepository;
import com.diaetologie.diaetologenbe.respository.PatientenfallRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/assessment/ernaehrung")
public class AssessmentErnaehrungController {

    private final AssessmentErnaehrungRepository repo;
    private final PatientenfallRepository fallRepo;

    public AssessmentErnaehrungController(AssessmentErnaehrungRepository repo,
                                          PatientenfallRepository fallRepo) {
        this.repo = repo;
        this.fallRepo = fallRepo;
    }

    @GetMapping("/fall/{fallId}")
    public List<AssessmentErnaehrung> perFall(@PathVariable UUID fallId) {
        return repo.findByFallId(fallId);
    }

    @PostMapping
    public ResponseEntity<?> speichern(@RequestBody Map<String, Object> body) {
        try {
            Map<String, Object> fallMap = (Map<String, Object>) body.get("fall");
            UUID fallId = UUID.fromString((String) fallMap.get("id"));
            Patientenfall fall = fallRepo.findById(fallId).orElseThrow();

            AssessmentErnaehrung e = new AssessmentErnaehrung();
            e.setFall(fall);
            e.setPalWert((String) body.get("palWert"));
            e.setEnergiebedarfKcal(toBD(body.get("energiebedarfKcal")));
            e.setEiweissbedarfG(toBD(body.get("eiweissbedarfG")));
            e.setFettbedarfG(toBD(body.get("fettbedarfG")));
            e.setKohlenhydratbedarfG(toBD(body.get("kohlenhydratbedarfG")));
            e.setBallaststoffbedarfG(toBD(body.get("ballaststoffbedarfG")));
            e.setFluessigkeitsbedarfMl(toBD(body.get("fluessigkeitsbedarfMl")));
            e.setKalorienaufnahme(toBD(body.get("kalorienaufnahme")));
            e.setFettaufnahme(toBD(body.get("fettaufnahme")));
            e.setKohlenhydrate(toBD(body.get("kohlenhydrate")));
            e.setZuckergetraenke((String) body.get("zuckergetraenke"));
            e.setAlkoholKonsum((String) body.get("alkoholKonsum"));
            e.setFertigprodukteKonsum((String) body.get("fertigprodukteKonsum"));
            e.setSuesswarenKonsum((String) body.get("suesswarenKonsum"));
            e.setKochbereitschaft(body.get("kochbereitschaft") != null && (Boolean) body.get("kochbereitschaft"));
            e.setBaecktGerne(body.get("baecktGerne") != null && (Boolean) body.get("baecktGerne"));
            e.setNotizen((String) body.get("notizen"));

            return ResponseEntity.ok(repo.save(e));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Fehler: " + ex.getMessage());
        }
    }

    private BigDecimal toBD(Object v) {
        if (v == null) return null;
        return new BigDecimal(v.toString());
    }

    @GetMapping("/fall/{fallId}/latest")
    public AssessmentErnaehrung getLatestByFallId(@PathVariable UUID fallId) {
        List<AssessmentErnaehrung> list = repo.findByFallId(fallId);
        return list.isEmpty() ? null : list.get(0);
    }
}