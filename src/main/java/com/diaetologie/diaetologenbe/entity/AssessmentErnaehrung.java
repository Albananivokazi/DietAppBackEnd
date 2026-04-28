package com.diaetologie.diaetologenbe.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;
@Entity
@Table(name = "assessment_ernaehrung")
public class AssessmentErnaehrung {
    @Id @GeneratedValue(strategy = GenerationType.UUID) private UUID id;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "fall_id") private Patientenfall fall;
    @Column(name = "pal_wert")              private String palWert;
    @Column(name = "energiebedarf_kcal")    private BigDecimal energiebedarfKcal;
    @Column(name = "eiweissbedarf_g")       private BigDecimal eiweissbedarfG;
    @Column(name = "fettbedarf_g")          private BigDecimal fettbedarfG;
    @Column(name = "kohlenhydratbedarf_g")  private BigDecimal kohlenhydratbedarfG;
    @Column(name = "ballaststoffbedarf_g")  private BigDecimal ballaststoffbedarfG;
    @Column(name = "fluessigkeitsbedarf_ml") private BigDecimal fluessigkeitsbedarfMl;
    @Column(name = "kalorienaufnahme")      private BigDecimal kalorienaufnahme;
    @Column(name = "fettaufnahme")          private BigDecimal fettaufnahme;
    @Column(name = "kohlenhydrate")         private BigDecimal kohlenhydrate;
    @Column(name = "energie_abweichung_pct") private BigDecimal energieAbweichungPct;
    @Column(name = "fett_abweichung_pct")   private BigDecimal fettAbweichungPct;
    @Column(name = "zuckergetraenke")       private String zuckergetraenke;
    @Column(name = "alkohol_konsum")        private String alkoholKonsum;
    @Column(name = "fertigprodukte_konsum") private String fertigprodukteKonsum;
    @Column(name = "suesswaren_konsum")     private String suesswarenKonsum;
    @Column(name = "kochbereitschaft")      private Boolean kochbereitschaft;
    @Column(name = "baeckt_gerne")          private Boolean baecktGerne;
    @Column(name = "notizen", columnDefinition = "TEXT") private String notizen;
    public AssessmentErnaehrung() {}
    public UUID getId() { return id; }
    public Patientenfall getFall() { return fall; }
    public void setFall(Patientenfall v) { this.fall = v; }
    public String getPalWert() { return palWert; }
    public void setPalWert(String v) { this.palWert = v; }
    public BigDecimal getEnergiebedarfKcal() { return energiebedarfKcal; }
    public void setEnergiebedarfKcal(BigDecimal v) { this.energiebedarfKcal = v; }
    public BigDecimal getEiweissbedarfG() { return eiweissbedarfG; }
    public void setEiweissbedarfG(BigDecimal v) { this.eiweissbedarfG = v; }
    public BigDecimal getFettbedarfG() { return fettbedarfG; }
    public void setFettbedarfG(BigDecimal v) { this.fettbedarfG = v; }
    public BigDecimal getKohlenhydratbedarfG() { return kohlenhydratbedarfG; }
    public void setKohlenhydratbedarfG(BigDecimal v) { this.kohlenhydratbedarfG = v; }
    public BigDecimal getBallaststoffbedarfG() { return ballaststoffbedarfG; }
    public void setBallaststoffbedarfG(BigDecimal v) { this.ballaststoffbedarfG = v; }
    public BigDecimal getFluessigkeitsbedarfMl() { return fluessigkeitsbedarfMl; }
    public void setFluessigkeitsbedarfMl(BigDecimal v) { this.fluessigkeitsbedarfMl = v; }
    public BigDecimal getKalorienaufnahme() { return kalorienaufnahme; }
    public void setKalorienaufnahme(BigDecimal v) { this.kalorienaufnahme = v; }
    public BigDecimal getFettaufnahme() { return fettaufnahme; }
    public void setFettaufnahme(BigDecimal v) { this.fettaufnahme = v; }
    public BigDecimal getKohlenhydrate() { return kohlenhydrate; }
    public void setKohlenhydrate(BigDecimal v) { this.kohlenhydrate = v; }
    public BigDecimal getEnergieAbweichungPct() { return energieAbweichungPct; }
    public void setEnergieAbweichungPct(BigDecimal v) { this.energieAbweichungPct = v; }
    public BigDecimal getFettAbweichungPct() { return fettAbweichungPct; }
    public void setFettAbweichungPct(BigDecimal v) { this.fettAbweichungPct = v; }
    public String getZuckergetraenke() { return zuckergetraenke; }
    public void setZuckergetraenke(String v) { this.zuckergetraenke = v; }
    public String getAlkoholKonsum() { return alkoholKonsum; }
    public void setAlkoholKonsum(String v) { this.alkoholKonsum = v; }
    public String getFertigprodukteKonsum() { return fertigprodukteKonsum; }
    public void setFertigprodukteKonsum(String v) { this.fertigprodukteKonsum = v; }
    public String getSuesswarenKonsum() { return suesswarenKonsum; }
    public void setSuesswarenKonsum(String v) { this.suesswarenKonsum = v; }
    public Boolean getKochbereitschaft() { return kochbereitschaft; }
    public void setKochbereitschaft(Boolean v) { this.kochbereitschaft = v; }
    public Boolean getBaecktGerne() { return baecktGerne; }
    public void setBaecktGerne(Boolean v) { this.baecktGerne = v; }
    public String getNotizen() { return notizen; }
    public void setNotizen(String v) { this.notizen = v; }
}