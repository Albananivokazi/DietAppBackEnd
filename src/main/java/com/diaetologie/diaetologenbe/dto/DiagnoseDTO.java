package com.diaetologie.diaetologenbe.dto;
import java.util.UUID;
public class DiagnoseDTO {
    private UUID fallId;
    private String problem;
    private String ursache;
    private String symptom;
    private String foerderfaktoren;
    private String barrieren;
    private Integer prioritaet;
    public DiagnoseDTO() {}
    public UUID getFallId() { return fallId; }
    public void setFallId(UUID v) { this.fallId = v; }
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