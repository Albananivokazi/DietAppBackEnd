package com.diaetologie.diaetologenbe.controller;
import com.diaetologie.diaetologenbe.dto.ZieleDTO;
import com.diaetologie.diaetologenbe.entity.Patientenfall;
import com.diaetologie.diaetologenbe.entity.Ziele;
import com.diaetologie.diaetologenbe.respository.PatientenfallRepository;
import com.diaetologie.diaetologenbe.respository.ZieleRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/api/ziele")
public class ZieleController {
    private final ZieleRepository repo;
    private final PatientenfallRepository fallRepo;
    public ZieleController(ZieleRepository repo, PatientenfallRepository fallRepo) {
        this.repo = repo; this.fallRepo = fallRepo;
    }
    @GetMapping("/fall/{fallId}")
    public List<Ziele> perFall(@PathVariable UUID fallId) { return repo.findByFallId(fallId); }
    @PostMapping
    public Ziele speichern(@RequestBody ZieleDTO dto) {
        Patientenfall fall = fallRepo.findById(dto.getFallId()).orElseThrow();
        Ziele z = new Ziele();
        z.setFall(fall);
        z.setLangfristigesZiel(dto.getLangfristigesZiel());
        z.setInterventionsziel(dto.getInterventionsziel());
        z.setZielGewicht(dto.getZielGewicht());
        z.setZielFettmasse(dto.getZielFettmasse());
        z.setZielKalorien(dto.getZielKalorien());
        z.setZielFettaufnahme(dto.getZielFettaufnahme());
        z.setZielDatum(dto.getZielDatum());
        z.setStatus(dto.getStatus() != null ? dto.getStatus() : "offen");
        return repo.save(z);
    }
}