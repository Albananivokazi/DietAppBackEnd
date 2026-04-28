package com.diaetologie.diaetologenbe.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.UUID;
@Entity
@Table(name = "diagnose")
public class Diagnose {
    @Id @GeneratedValue(strategy = GenerationType.UUID) private UUID id;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "fall_id") private Patientenfall fall;
    @Column(name = "problem",         columnDefinition = "TEXT") private String problem;
    @Column(name = "ursache",         columnDefinition = "TEXT") private String ursache;
    @Column(name = "symptom",         columnDefinition = "TEXT") private String symptom;
    @Column(name = "foerderfaktoren", columnDefinition = "TEXT") private String foerderfaktoren;
    @Column(name = "barrieren",       columnDefinition = "TEXT") private String barrieren;
    @Column(name = "prioritaet")                                 private Integer prioritaet;
    public Diagnose() {}
    public UUID getId() { return id; }
    public Patientenfall getFall() { return fall; }
    public void setFall(Patientenfall v) { this.fall = v; }
    public String getProblem() { return problem; }
    public void setProblem(String v) { this.problem = v; }
    public String getUrsache() { return ursache; }
    public void setUrsache(String v) { this.ursache = v; }
    public String getSymptom() { return symptom; }
    public void setSymptom(String v) { this.symptom = v; }
    public String getFoerderfaktoren() { return foerderfaktoren; }
    public void setFoerderfaktoren(String v) { this.foerderfaktoren = v; }
    public String getBarrieren() { return barrieren; }
    public void setBarrieren(String v) { this.barrieren = v; }
    public Integer getPrioritaet() { return prioritaet; }
    public void setPrioritaet(Integer v) { this.prioritaet = v; }
}