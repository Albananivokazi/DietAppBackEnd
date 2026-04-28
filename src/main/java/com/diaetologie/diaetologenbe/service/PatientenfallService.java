package com.diaetologie.diaetologenbe.service;
import com.diaetologie.diaetologenbe.dto.NeuerFallRequest;
import com.diaetologie.diaetologenbe.dto.PatientenfallDTO;
import com.diaetologie.diaetologenbe.entity.PatientStammdaten;
import com.diaetologie.diaetologenbe.entity.Patientenfall;
import com.diaetologie.diaetologenbe.respository.PatientStammdatenRepository;
import com.diaetologie.diaetologenbe.respository.PatientenfallRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PatientenfallService {
    private final PatientenfallRepository fallRepo;
    private final PatientStammdatenRepository patientRepo;

    public PatientenfallService(PatientenfallRepository fallRepo,
                                PatientStammdatenRepository patientRepo) {
        this.fallRepo = fallRepo; this.patientRepo = patientRepo;
    }

    public List<PatientenfallDTO> alleAlsDto() {
        return fallRepo.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Transactional
    public PatientenfallDTO neuerFall(NeuerFallRequest req) {
        PatientStammdaten p = new PatientStammdaten();
        p.setAnonymerCode(generiereCode());
        p.setVorname(req.getVorname());
        p.setNachname(req.getNachname());
        p.setAlterJahre(req.getAlterJahre());
        p.setGeschlecht(req.getGeschlecht());
        patientRepo.save(p);
        Patientenfall fall = new Patientenfall();
        fall.setPatient(p);
        fallRepo.save(fall);
        return toDto(fall);
    }

    public PatientenfallDTO perIdAlsDto(UUID id) {
        return fallRepo.findById(id).map(this::toDto).orElseThrow();
    }

    private PatientenfallDTO toDto(Patientenfall fall) {
        PatientenfallDTO dto = new PatientenfallDTO();
        dto.setId(fall.getId());
        dto.setErstelltAm(fall.getErstelltAm() != null ? fall.getErstelltAm().toString() : null);
        if (fall.getPatient() != null) {
            dto.setPatientId(fall.getPatient().getId());
            dto.setAlterJahre(fall.getPatient().getAlterJahre());
            dto.setGeschlecht(fall.getPatient().getGeschlecht());
            dto.setAnonymerCode(fall.getPatient().getAnonymerCode());
            dto.setVorname(fall.getPatient().getVorname());
            dto.setNachname(fall.getPatient().getNachname());
        }
        return dto;
    }

    private String generiereCode() {
        String z = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";
        Random r = new Random();
        String code;
        do {
            StringBuilder sb = new StringBuilder("ADI-")
                    .append(java.time.Year.now().getValue()).append("-");
            for (int i = 0; i < 4; i++) sb.append(z.charAt(r.nextInt(z.length())));
            code = sb.toString();
        } while (patientRepo.existsByAnonymerCode(code));
        return code;
    }
}