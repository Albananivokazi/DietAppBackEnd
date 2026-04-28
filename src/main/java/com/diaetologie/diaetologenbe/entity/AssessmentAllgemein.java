package com.diaetologie.diaetologenbe.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.UUID;
@Entity
@Table(name = "assessment_allgemein")
public class AssessmentAllgemein {
    @Id @GeneratedValue(strategy = GenerationType.UUID) private UUID id;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "fall_id") private Patientenfall fall;
    @Column(name = "muedigkeit_level")        private String muedigkeitLevel;
    @Column(name = "kopfschmerzen")           private Boolean kopfschmerzen;
    @Column(name = "wissen_ernaehrung_level") private String wissenErnaehrungLevel;
    @Column(name = "motivation_level")        private String motivationLevel;
    @Column(name = "klienten_perspektive", columnDefinition = "TEXT") private String klientenPerspektive;
    @Column(name = "notizen", columnDefinition = "TEXT") private String notizen;
    public AssessmentAllgemein() {}
    public UUID getId() { return id; }
    public Patientenfall getFall() { return fall; }
    public void setFall(Patientenfall v) { this.fall = v; }
    public String getMuedigkeitLevel() { return muedigkeitLevel; }
    public void setMuedigkeitLevel(String v) { this.muedigkeitLevel = v; }
    public Boolean getKopfschmerzen() { return kopfschmerzen; }
    public void setKopfschmerzen(Boolean v) { this.kopfschmerzen = v; }
    public String getWissenErnaehrungLevel() { return wissenErnaehrungLevel; }
    public void setWissenErnaehrungLevel(String v) { this.wissenErnaehrungLevel = v; }
    public String getMotivationLevel() { return motivationLevel; }
    public void setMotivationLevel(String v) { this.motivationLevel = v; }
    public String getKlientenPerspektive() { return klientenPerspektive; }
    public void setKlientenPerspektive(String v) { this.klientenPerspektive = v; }
    public String getNotizen() { return notizen; }
    public void setNotizen(String v) { this.notizen = v; }
}