package com.diaetologie.diaetologenbe.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "patient_stammdaten")
public class PatientStammdaten {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "anonymer_code") private String anonymerCode;
    @Column(name = "vorname")       private String vorname;
    @Column(name = "nachname")      private String nachname;
    @Column(name = "alter_jahre")   private Integer alterJahre;
    @Column(name = "geschlecht")    private String geschlecht;
    @Column(name = "erstellt_am")   private LocalDateTime erstelltAm;
    public PatientStammdaten() {}
    @PrePersist void setzeDatum() { this.erstelltAm = LocalDateTime.now(); }
    public UUID getId() { return id; }
    public String getAnonymerCode() { return anonymerCode; }
    public void setAnonymerCode(String v) { this.anonymerCode = v; }
    public String getVorname() { return vorname; }
    public void setVorname(String v) { this.vorname = v; }
    public String getNachname() { return nachname; }
    public void setNachname(String v) { this.nachname = v; }
    public Integer getAlterJahre() { return alterJahre; }
    public void setAlterJahre(Integer v) { this.alterJahre = v; }
    public String getGeschlecht() { return geschlecht; }
    public void setGeschlecht(String v) { this.geschlecht = v; }
    public LocalDateTime getErstelltAm() { return erstelltAm; }
    public void setErstelltAm(LocalDateTime v) { this.erstelltAm = v; }
}