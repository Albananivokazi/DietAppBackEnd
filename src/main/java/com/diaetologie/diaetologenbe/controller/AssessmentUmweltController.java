package com.diaetologie.diaetologenbe.controller;
import com.diaetologie.diaetologenbe.entity.AssessmentUmwelt;
import com.diaetologie.diaetologenbe.entity.Patientenfall;
import com.diaetologie.diaetologenbe.respository.AssessmentUmweltRepository;
import com.diaetologie.diaetologenbe.respository.PatientenfallRepository;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController
@RequestMapping("/api/assessment/umwelt")
public class AssessmentUmweltController {
    private final AssessmentUmweltRepository repo;
    private final PatientenfallRepository fallRepo;
    public AssessmentUmweltController(AssessmentUmweltRepository repo, PatientenfallRepository fallRepo) {
        this.repo = repo; this.fallRepo = fallRepo;
    }
    @GetMapping("/fall/{fallId}")
    public List<AssessmentUmwelt> perFall(@PathVariable UUID fallId) { return repo.findByFallId(fallId); }
    @PostMapping
    public AssessmentUmwelt speichern(@RequestBody Map<String, Object> body) {
        UUID fallId = UUID.fromString((String) body.get("fallId"));
        Patientenfall fall = fallRepo.findById(fallId).orElseThrow();
        AssessmentUmwelt u = new AssessmentUmwelt();
        u.setFall(fall);
        u.setWohnsituation((String) body.get("wohnsituation"));
        u.setFamiliaereUnterstuetzung((String) body.get("familiaereUnterstuetzung"));
        u.setSozialarbeit((Boolean) body.getOrDefault("sozialarbeit", false));
        u.setPsychotherapieGeplant((Boolean) body.getOrDefault("psychotherapieGeplant", false));
        u.setBeruf((String) body.get("beruf"));
        u.setKoerperlicheArbeit((Boolean) body.getOrDefault("koerperlicheArbeit", false));
        u.setAktivitaetslevel((String) body.get("aktivitaetslevel"));
        u.setFreundeskreisSport((Boolean) body.getOrDefault("freundeskreisSport", false));
        u.setNotizen((String) body.get("notizen"));
        return repo.save(u);
    }
}