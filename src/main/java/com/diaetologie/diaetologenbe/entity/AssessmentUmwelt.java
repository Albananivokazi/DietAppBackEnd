package com.diaetologie.diaetologenbe.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.UUID;
@Entity
@Table(name = "assessment_umwelt")
public class AssessmentUmwelt {
    @Id @GeneratedValue(strategy = GenerationType.UUID) private UUID id;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "fall_id") private Patientenfall fall;
    @Column(name = "wohnsituation")             private String  wohnsituation;
    @Column(name = "familiaere_unterstuetzung") private String  familiaereUnterstuetzung;
    @Column(name = "sozialarbeit")              private Boolean sozialarbeit;
    @Column(name = "psychotherapie_geplant")    private Boolean psychotherapieGeplant;
    @Column(name = "beruf")                     private String  beruf;
    @Column(name = "koerperliche_arbeit")       private Boolean koerperlicheArbeit;
    @Column(name = "aktivitaetslevel")          private String  aktivitaetslevel;
    @Column(name = "freundeskreis_sport")       private Boolean freundeskreisSport;
    @Column(name = "notizen", columnDefinition = "TEXT") private String notizen;
    public AssessmentUmwelt() {}
    public UUID getId() { return id; }
    public Patientenfall getFall() { return fall; }
    public void setFall(Patientenfall v) { this.fall = v; }
    public String getWohnsituation() { return wohnsituation; }
    public void setWohnsituation(String v) { this.wohnsituation = v; }
    public String getFamiliaereUnterstuetzung() { return familiaereUnterstuetzung; }
    public void setFamiliaereUnterstuetzung(String v) { this.familiaereUnterstuetzung = v; }
    public Boolean getSozialarbeit() { return sozialarbeit; }
    public void setSozialarbeit(Boolean v) { this.sozialarbeit = v; }
    public Boolean getPsychotherapieGeplant() { return psychotherapieGeplant; }
    public void setPsychotherapieGeplant(Boolean v) { this.psychotherapieGeplant = v; }
    public String getBeruf() { return beruf; }
    public void setBeruf(String v) { this.beruf = v; }
    public Boolean getKoerperlicheArbeit() { return koerperlicheArbeit; }
    public void setKoerperlicheArbeit(Boolean v) { this.koerperlicheArbeit = v; }
    public String getAktivitaetslevel() { return aktivitaetslevel; }
    public void setAktivitaetslevel(String v) { this.aktivitaetslevel = v; }
    public Boolean getFreundeskreisSport() { return freundeskreisSport; }
    public void setFreundeskreisSport(Boolean v) { this.freundeskreisSport = v; }
    public String getNotizen() { return notizen; }
    public void setNotizen(String v) { this.notizen = v; }
}