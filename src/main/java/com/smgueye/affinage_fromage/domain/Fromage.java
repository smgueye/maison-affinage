package com.smgueye.affinage_fromage.domain;

import com.smgueye.affinage_fromage.common.Entity;
import com.smgueye.affinage_fromage.common.exceptions.NotImplementedException;
import com.smgueye.affinage_fromage.domain.etat.AucunEtat;
import com.smgueye.affinage_fromage.domain.etat.Etat;
import com.smgueye.affinage_fromage.domain.etat.EtatRecu;
import com.smgueye.affinage_fromage.domain.incident.Incident;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Fromage extends Entity {

  @Getter private FromageId id;
  private String name;
  private Famille famille;
  private Poids poids;
  private LocalDate dateDeReception;
  @Getter private Etat etat;
  private ArtisantId artisantId;
  private CaveAffinage caveAffinage;
  private List<SoinAffinage> planAffinage = new ArrayList<>();;
  private List<SoinAffinage> soinsRealises = new ArrayList<>();;
  private List<Incident> incidents = new ArrayList<>();;

  public Fromage(FromageId id, String name, Poids poids, LocalDate date, Famille famille, ArtisantId artisantId) {
    this.setId(id);
    this.setName(name);
    this.setPoids(poids);
    this.setDateDeReception(date);
    this.setEtat(new AucunEtat());
    this.setFamille(famille);
    this.setArtisantId(artisantId);
  }

  protected void setId(FromageId fromageId) {
    // TODO - Control : is attr clean ?
    this.id = fromageId;
  }

  protected void setName(String name) {
    // TODO - Control : is attr clean ?
    this.name = name;
  }

  protected void setPoids(Poids poids) {
    // TODO - Control : is attr clean ?
    this.poids = poids;
  }

  protected void setDateDeReception(LocalDate dateDeReception) {
    // TODO - Control : is attr clean ?
    this.dateDeReception = dateDeReception;
  }
  
  protected void setEtat(Etat etat) {
    // TODO - Control : is attr clean ?
    this.etat = etat;
  }
  
  protected void setFamille(Famille famille) {
    // TODO - Control : is attr clean ?
    this.famille = famille;
  }

  protected void setArtisantId(ArtisantId artisantId) {
    // TODO - Control : is attr clean ?
    this.artisantId = artisantId;
  }

  public void placementEnCave(CaveAffinage caveAffinage) {
    // TODO - Control : is attr clean ?
    throw new NotImplementedException();
  }

  public void maturation() {
    // TODO - Control : is attr clean ?
    throw new NotImplementedException();
  }

  public float controlPoids() {
    // TODO - Control : is attr clean ?
    throw new NotImplementedException();
  }

  public void recu() {
    this.etat = new EtatRecu();
  }

  public void bloquer() {
    // TODO - Control : is attr clean ?
    throw new NotImplementedException();
  }

  public void pret() {
    // TODO - Control : is attr clean ?
    throw new NotImplementedException();
  }

  public boolean estRecu() {
    return this.etat.getClass().equals(EtatRecu.class);
  }
}
