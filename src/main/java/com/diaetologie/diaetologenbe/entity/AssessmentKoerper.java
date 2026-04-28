package com.diaetologie.diaetologenbe.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;
@Entity
@Table(name = "assessment_koerper")
public class AssessmentKoerper {
    @Id @GeneratedValue(strategy = GenerationType.UUID) private UUID id;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "fall_id") private Patientenfall fall;
    @Column(name = "groesse")                    private BigDecimal groesse;
    @Column(name = "gewicht")                    private BigDecimal gewicht;
    @Column(name = "bmi")                        private BigDecimal bmi;
    @Column(name = "bmi_perzentile")             private String bmiPerzentile;
    @Column(name = "blutdruck_systolisch")       private Integer blutdruckSystolisch;
    @Column(name = "blutdruck_diastolisch")      private Integer blutdruckDiastolisch;
    @Column(name = "fettmasse")                  private BigDecimal fettmasse;
    @Column(name = "fettmasse_ideal_min")        private BigDecimal fettmasseIdealMin;
    @Column(name = "fettmasse_ideal_max")        private BigDecimal fettmasseIdealMax;
    @Column(name = "muskelmasse")                private BigDecimal muskelmasse;
    @Column(name = "muskelmasse_hoch")           private Boolean muskelmasseHoch;
    @Column(name = "phasenwinkel")               private BigDecimal phasenwinkel;
    @Column(name = "koerperzellmasse")           private BigDecimal koerperzellmasse;
    @Column(name = "koerperzellmasse_ideal_min") private BigDecimal koerperzellmasseIdealMin;
    @Column(name = "koerperzellmasse_ideal_max") private BigDecimal koerperzellmasseIdealMax;
    @Column(name = "laborparameter_status")      private String laborparameterStatus;
    public AssessmentKoerper() {}
    public UUID getId() { return id; }
    public Patientenfall getFall() { return fall; }
    public void setFall(Patientenfall v) { this.fall = v; }
    public BigDecimal getGroesse() { return groesse; }
    public void setGroesse(BigDecimal v) { this.groesse = v; }
    public BigDecimal getGewicht() { return gewicht; }
    public void setGewicht(BigDecimal v) { this.gewicht = v; }
    public BigDecimal getBmi() { return bmi; }
    public void setBmi(BigDecimal v) { this.bmi = v; }
    public String getBmiPerzentile() { return bmiPerzentile; }
    public void setBmiPerzentile(String v) { this.bmiPerzentile = v; }
    public Integer getBlutdruckSystolisch() { return blutdruckSystolisch; }
    public void setBlutdruckSystolisch(Integer v) { this.blutdruckSystolisch = v; }
    public Integer getBlutdruckDiastolisch() { return blutdruckDiastolisch; }
    public void setBlutdruckDiastolisch(Integer v) { this.blutdruckDiastolisch = v; }
    public BigDecimal getFettmasse() { return fettmasse; }
    public void setFettmasse(BigDecimal v) { this.fettmasse = v; }
    public BigDecimal getFettmasseIdealMin() { return fettmasseIdealMin; }
    public void setFettmasseIdealMin(BigDecimal v) { this.fettmasseIdealMin = v; }
    public BigDecimal getFettmasseIdealMax() { return fettmasseIdealMax; }
    public void setFettmasseIdealMax(BigDecimal v) { this.fettmasseIdealMax = v; }
    public BigDecimal getMuskelmasse() { return muskelmasse; }
    public void setMuskelmasse(BigDecimal v) { this.muskelmasse = v; }
    public Boolean getMuskelmasseHoch() { return muskelmasseHoch; }
    public void setMuskelmasseHoch(Boolean v) { this.muskelmasseHoch = v; }
    public BigDecimal getPhasenwinkel() { return phasenwinkel; }
    public void setPhasenwinkel(BigDecimal v) { this.phasenwinkel = v; }
    public BigDecimal getKoerperzellmasse() { return koerperzellmasse; }
    public void setKoerperzellmasse(BigDecimal v) { this.koerperzellmasse = v; }
    public BigDecimal getKoerperzellmasseIdealMin() { return koerperzellmasseIdealMin; }
    public void setKoerperzellmasseIdealMin(BigDecimal v) { this.koerperzellmasseIdealMin = v; }
    public BigDecimal getKoerperzellmasseIdealMax() { return koerperzellmasseIdealMax; }
    public void setKoerperzellmasseIdealMax(BigDecimal v) { this.koerperzellmasseIdealMax = v; }
    public String getLaborparameterStatus() { return laborparameterStatus; }
    public void setLaborparameterStatus(String v) { this.laborparameterStatus = v; }
}