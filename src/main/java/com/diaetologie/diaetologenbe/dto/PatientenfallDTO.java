package com.diaetologie.diaetologenbe.dto;
import java.util.UUID;
public class PatientenfallDTO {
    private UUID id;
    private UUID patientId;
    private String erstelltAm;
    private Integer alterJahre;
    private String geschlecht;
    private String anonymerCode;
    private String vorname;
    private String nachname;
    public PatientenfallDTO() {}
    public UUID getId() { return id; }
    public void setId(UUID v) { this.id = v; }
    public UUID getPatientId() { return patientId; }
    public void setPatientId(UUID v) { this.patientId = v; }
    public String getErstelltAm() { return erstelltAm; }
    public void setErstelltAm(String v) { this.erstelltAm = v; }
    public Integer getAlterJahre() { return alterJahre; }
    public void setAlterJahre(Integer v) { this.alterJahre = v; }
    public String getGeschlecht() { return geschlecht; }
    public void setGeschlecht(String v) { this.geschlecht = v; }
    public String getAnonymerCode() { return anonymerCode; }
    public void setAnonymerCode(String v) { this.anonymerCode = v; }
    public String getVorname() { return vorname; }
    public void setVorname(String v) { this.vorname = v; }
    public String getNachname() { return nachname; }
    public void setNachname(String v) { this.nachname = v; }
}