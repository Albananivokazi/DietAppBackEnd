package com.diaetologie.diaetologenbe.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Table(name = "patientenfall")
public class Patientenfall {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id")
    private PatientStammdaten patient;
    @Column(name = "erstellt_am")  private LocalDateTime erstelltAm;
    @Column(name = "erstellt_von") private String erstelltVon;
    public Patientenfall() {}
    @PrePersist void setzeDatum() { this.erstelltAm = LocalDateTime.now(); }
    public UUID getId() { return id; }
    public PatientStammdaten getPatient() { return patient; }
    public void setPatient(PatientStammdaten v) { this.patient = v; }
    public LocalDateTime getErstelltAm() { return erstelltAm; }
    public String getErstelltVon() { return erstelltVon; }
    public void setErstelltVon(String v) { this.erstelltVon = v; }
}