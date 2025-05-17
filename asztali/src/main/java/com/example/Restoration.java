package com.example;

import java.time.LocalDate;


public class Restoration {
  Integer id;
  Integer paintingid;
  LocalDate datum;
  String muvelet;
  String megjegyzes;
  public Restoration(Integer id, Integer paintingid, LocalDate datum, String muvelet, String megjegyzes) {
    this.id = id;
    this.paintingid = paintingid;
    this.datum = datum;
    this.muvelet = muvelet;
    this.megjegyzes = megjegyzes;
  }
  public Restoration() {
  }
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public Integer getPaintingid() {
    return paintingid;
  }
  public void setPaintingid(Integer paintingid) {
    this.paintingid = paintingid;
  }
  public LocalDate getDatum() {
    return datum;
  }
  public void setDatum(LocalDate datum) {
    this.datum = datum;
  }
  public String getMuvelet() {
    return muvelet;
  }
  public void setMuvelet(String muvelet) {
    this.muvelet = muvelet;
  }
  public String getMegjegyzes() {
    return megjegyzes;
  }
  public void setMegjegyzes(String megjegyzes) {
    this.megjegyzes = megjegyzes;
  }
  
}
