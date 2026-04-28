package com.diaetologie.diaetologenbe.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
@Entity
@Table(name = "ziele")
public class Ziele {
    @Id @GeneratedValue(strategy = GenerationType.UUID) private UUID id;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "fall_id") private Patientenfall fall;
    @Column(name = "langfristiges_ziel") private String langfristigesZiel;
    @Column(name = "interventionsziel")  private String interventionsziel;
    @Column(name = "ziel_gewicht")       private BigDecimal zielGewicht;
    @Column(name = "ziel_fettmasse")     private BigDecimal zielFettmasse;
    @Column(name = "ziel_kalorien")      private BigDecimal zielKalorien;
    @Column(name = "ziel_fettaufnahme")  private BigDecimal zielFettaufnahme;
    @Column(name = "ziel_datum")         private LocalDate zielDatum;
    @Column(name = "status")             private String status;
    public Ziele() {}
    public UUID getId() { return id; }
    public Patientenfall getFall() { return fall; }
    public void setFall(Patientenfall v) { this.fall = v; }
    public String getLangfristigesZiel() { return langfristigesZiel; }
    public void setLangfristigesZiel(String v) { this.langfristigesZiel = v; }
    public String getInterventionsziel() { return interventionsziel; }
    public void setInterventionsziel(String v) { this.interventionsziel = v; }
    public BigDecimal getZielGewicht() { return zielGewicht; }
    public void setZielGewicht(BigDecimal v) { this.zielGewicht = v; }
    public BigDecimal getZielFettmasse() { return zielFettmasse; }
    public void setZielFettmasse(BigDecimal v) { this.zielFettmasse = v; }
    public BigDecimal getZielKalorien() { return zielKalorien; }
    public void setZielKalorien(BigDecimal v) { this.zielKalorien = v; }
    public BigDecimal getZielFettaufnahme() { return zielFettaufnahme; }
    public void setZielFettaufnahme(BigDecimal v) { this.zielFettaufnahme = v; }
    public LocalDate getZielDatum() { return zielDatum; }
    public void setZielDatum(LocalDate v) { this.zielDatum = v; }
    public String getStatus() { return status; }
    public void setStatus(String v) { this.status = v; }
}