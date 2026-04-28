package com.diaetologie.diaetologenbe.dto;
public class NeuerFallRequest {
    private String vorname;
    private String nachname;
    private Integer alterJahre;
    private String geschlecht;
    public NeuerFallRequest() {}
    public String getVorname() { return vorname; }
    public void setVorname(String v) { this.vorname = v; }
    public String getNachname() { return nachname; }
    public void setNachname(String v) { this.nachname = v; }
    public Integer getAlterJahre() { return alterJahre; }
    public void setAlterJahre(Integer v) { this.alterJahre = v; }
    public String getGeschlecht() { return geschlecht; }
    public void setGeschlecht(String v) { this.geschlecht = v; }
}