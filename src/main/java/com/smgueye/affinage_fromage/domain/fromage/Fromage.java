package com.smgueye.affinage_fromage.domain.fromage;

import com.smgueye.affinage_fromage.common.Entity;
import com.smgueye.affinage_fromage.common.exceptions.NotImplementedException;
import com.smgueye.affinage_fromage.domain.*;
import com.smgueye.affinage_fromage.domain.cave.CaveAffinage;
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
  private ArtisanId artisanId;
  private CaveAffinage caveAffinage;
  private List<SoinAffinage> planAffinage = new ArrayList<>();;
  private List<SoinAffinage> soinsRealises = new ArrayList<>();;
  private List<Incident> incidents = new ArrayList<>();;

  public Fromage(FromageId id, String name, Poids poids, LocalDate date, Famille famille, ArtisanId artisanId) {
    this.setId(id);
    this.setName(name);
    this.setPoids(poids);
    this.setDateDeReception(date);
    this.setEtat(new AucunEtat());
    this.setFamille(famille);
    this.setArtisanId(artisanId);
  }

  protected void setId(FromageId fromageId) {
    this.verifieArgumentNonNull(fromageId, "L'identifiant du fromage est requis.");
    this.id = fromageId;
  }

  protected void setName(String name) {
    this.verifieArgumentNonNullNiVide(name, "Le nom du fromage est requis.");
    this.name = name;
  }

  protected void setPoids(Poids poids) {
    this.verifieArgumentNonNull(poids, "Le poids du fromage est requis.");
    this.poids = poids;
  }

  protected void setDateDeReception(LocalDate dateDeReception) {
    this.verifieArgumentNonNull(dateDeReception, "La date de reception du fromage est requise.");
    this.dateDeReception = dateDeReception;
  }
  
  protected void setEtat(Etat etat) {
    this.verifieArgumentNonNull(etat, "Le état du fromage est requis.");
    this.etat = etat;
  }
  
  protected void setFamille(Famille famille) {
    this.verifieArgumentNonNull(famille,  "La famille du fromage est requise.");
    this.famille = famille;
  }

  protected void setArtisanId(ArtisanId artisanId) {
    this.verifieArgumentNonNull(artisanId, "L'identifiant de l'artisan est requis.");
    this.artisanId = artisanId;
  }

  public void placementEnCave(CaveAffinage caveAffinage) {
    throw new NotImplementedException();
  }

  public void maturation() {
    throw new NotImplementedException();
  }

  public float controlPoids() {
    throw new NotImplementedException();
  }

  public void recu() {
    this.etat = new EtatRecu();
  }

  public void bloquer() {
    throw new NotImplementedException();
  }

  public void pret() {
    throw new NotImplementedException();
  }

  public boolean estRecu() {
    return this.etat.getClass().equals(EtatRecu.class);
  }
}
