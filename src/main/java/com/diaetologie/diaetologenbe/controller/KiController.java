package com.diaetologie.diaetologenbe.controller;

import com.diaetologie.diaetologenbe.entity.*;
import com.diaetologie.diaetologenbe.respository.*;
import com.diaetologie.diaetologenbe.service.GroqService;  // ← Ändere hier!
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/ki")
public class KiController {

    private final GroqService groqService;  // ← Ändere hier!
    private final PatientenfallRepository fallRepo;
    private final AssessmentKoerperRepository koerperRepo;
    private final AssessmentErnaehrungRepository ernaehrungRepo;
    private final DiagnoseRepository diagnoseRepo;
    private final AssessmentAllgemeinRepository allgemeinRepo;
    private final KiEntscheidungRepository kiRepo;

    public KiController(GroqService groqService,  // ← Ändere hier!
                        PatientenfallRepository fallRepo,
                        AssessmentKoerperRepository koerperRepo,
                        AssessmentErnaehrungRepository ernaehrungRepo,
                        DiagnoseRepository diagnoseRepo,
                        AssessmentAllgemeinRepository allgemeinRepo,
                        KiEntscheidungRepository kiRepo) {
        this.groqService = groqService;  // ← Ändere hier!
        this.fallRepo = fallRepo;
        this.koerperRepo = koerperRepo;
        this.ernaehrungRepo = ernaehrungRepo;
        this.diagnoseRepo = diagnoseRepo;
        this.allgemeinRepo = allgemeinRepo;
        this.kiRepo = kiRepo;
    }

    @PostMapping("/empfehlung/{fallId}")
    public ResponseEntity<?> empfehlung(@PathVariable UUID fallId) {
        try {
            Patientenfall fall = fallRepo.findById(fallId).orElseThrow();

            List<AssessmentKoerper>    kl = koerperRepo.findByFallId(fallId);
            List<AssessmentErnaehrung> el = ernaehrungRepo.findByFallId(fallId);
            List<Diagnose>             dl = diagnoseRepo.findByFallId(fallId);
            List<AssessmentAllgemein>  al = allgemeinRepo.findByFallId(fallId);

            AssessmentKoerper    k = kl.isEmpty() ? null : kl.get(0);
            AssessmentErnaehrung e = el.isEmpty() ? null : el.get(0);
            Diagnose             d = dl.isEmpty() ? null : dl.get(0);
            AssessmentAllgemein  a = al.isEmpty() ? null : al.get(0);

            // Call Groq instead of Gemini
            String antwort = groqService.ernaehrungsempfehlung(
                    k != null && k.getBmi() != null ? k.getBmi().doubleValue() : null,
                    k != null && k.getFettmasse() != null ? k.getFettmasse().doubleValue() : null,
                    k != null && k.getFettmasseIdealMax() != null ? k.getFettmasseIdealMax().doubleValue() : null,
                    k != null ? k.getBlutdruckSystolisch() : null,
                    k != null ? k.getBlutdruckDiastolisch() : null,
                    e != null && e.getEnergiebedarfKcal() != null ? e.getEnergiebedarfKcal().doubleValue() : null,
                    e != null && e.getKalorienaufnahme() != null ? e.getKalorienaufnahme().doubleValue() : null,
                    e != null && e.getFettbedarfG() != null ? e.getFettbedarfG().doubleValue() : null,
                    e != null && e.getFettaufnahme() != null ? e.getFettaufnahme().doubleValue() : null,
                    e != null ? e.getZuckergetraenke() : null,
                    e != null ? e.getAlkoholKonsum() : null,
                    e != null ? e.getFertigprodukteKonsum() : null,
                    e != null ? e.getSuesswarenKonsum() : null,
                    e != null ? e.getKochbereitschaft() : null,
                    a != null ? a.getMotivationLevel() : null,
                    d != null ? d.getProblem() : null,
                    d != null ? d.getUrsache() : null,
                    d != null ? d.getSymptom() : null
            );

            // Save to database
            KiEntscheidung ki = new KiEntscheidung();
            ki.setFall(fall);
            ki.setEingabeJson("Klinische Daten für Fall: " + fallId);
            ki.setAusgabeJson(antwort);
            kiRepo.save(ki);

            return ResponseEntity.ok(Map.of(
                    "empfehlung", antwort,
                    "gespeichert", true,
                    "service", "Groq"
            ));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().body(Map.of("fehler", ex.getMessage()));
        }
    }

    @GetMapping("/fall/{fallId}")
    public ResponseEntity<?> letzteEmpfehlung(@PathVariable UUID fallId) {
        List<KiEntscheidung> list = kiRepo.findByFallIdOrderByErstelltAmDesc(fallId);
        if (list.isEmpty()) {
            return ResponseEntity.ok(Map.of("message", "Keine Empfehlungen für diesen Patienten"));
        }
        return ResponseEntity.ok(Map.of(
                "empfehlung", list.get(0).getAusgabeJson(),
                "erstelltAm", list.get(0).getErstelltAm().toString()
        ));
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok(Map.of("status", "working", "service", "Groq"));
    }
}