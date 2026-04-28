package com.diaetologie.diaetologenbe.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
@Entity
@Table(name = "monitoring")
public class Monitoring {
    @Id @GeneratedValue(strategy = GenerationType.UUID) private UUID id;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "fall_id") private Patientenfall fall;
    @Column(name = "datum")            private LocalDate datum;
    @Column(name = "gewicht")          private BigDecimal gewicht;
    @Column(name = "fettmasse")        private BigDecimal fettmasse;
    @Column(name = "kalorienaufnahme") private BigDecimal kalorienaufnahme;
    @Column(name = "fettaufnahme")     private BigDecimal fettaufnahme;
    @Column(name = "zielerreichung")   private String zielerreichung;
    @Column(name = "foerdernde_faktoren", columnDefinition = "TEXT") private String foerderndefaktoren;
    @Column(name = "hemmende_faktoren",   columnDefinition = "TEXT") private String hemmendefaktoren;
    @Column(name = "notizen",             columnDefinition = "TEXT") private String notizen;
    public Monitoring() {}
    public UUID getId() { return id; }
    public Patientenfall getFall() { return fall; }
    public void setFall(Patientenfall v) { this.fall = v; }
    public LocalDate getDatum() { return datum; }
    public void setDatum(LocalDate v) { this.datum = v; }
    public BigDecimal getGewicht() { return gewicht; }
    public void setGewicht(BigDecimal v) { this.gewicht = v; }
    public BigDecimal getFettmasse() { return fettmasse; }
    public void setFettmasse(BigDecimal v) { this.fettmasse = v; }
    public BigDecimal getKalorienaufnahme() { return kalorienaufnahme; }
    public void setKalorienaufnahme(BigDecimal v) { this.kalorienaufnahme = v; }
    public BigDecimal getFettaufnahme() { return fettaufnahme; }
    public void setFettaufnahme(BigDecimal v) { this.fettaufnahme = v; }
    public String getZielerreichung() { return zielerreichung; }
    public void setZielerreichung(String v) { this.zielerreichung = v; }
    public String getFoerderndefaktoren() { return foerderndefaktoren; }
    public void setFoerderndefaktoren(String v) { this.foerderndefaktoren = v; }
    public String getHemmendefaktoren() { return hemmendefaktoren; }
    public void setHemmendefaktoren(String v) { this.hemmendefaktoren = v; }
    public String getNotizen() { return notizen; }
    public void setNotizen(String v) { this.notizen = v; }
}