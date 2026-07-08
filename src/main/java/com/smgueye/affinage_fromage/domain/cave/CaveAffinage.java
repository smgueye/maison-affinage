package com.smgueye.affinage_fromage.domain.cave;

import com.smgueye.affinage_fromage.commun.Entite;
import com.smgueye.affinage_fromage.domain.fromage.Fromage;
import com.smgueye.affinage_fromage.domain.fromage.FromageId;

import java.util.HashSet;
import java.util.Set;

public class CaveAffinage extends Entite {
  private CaveAffinageId id;
  private int capaciteMaximale;
  private double temperatureCible;
  private double humiditeCible;
  private Set<FromageId> fromagesEnCave = new HashSet<FromageId>();

  public CaveAffinage(CaveAffinageId id, int capaciteMaximale, double temperatureCible, double humiditeCible) {
    setId(id);
    setCapaciteMaximale(capaciteMaximale);
    setTemperatureCible(temperatureCible);
    setHumiditeCible(humiditeCible);
  }

  public void place(Fromage unFromage) {
    fromagesEnCave.add(unFromage.id());
  }

  public boolean accueilleDeja(FromageId unFromageId) {
    return fromagesEnCave.contains(unFromageId);
  }

  public boolean accueillePasEncore(FromageId unFromageId) {
    return !accueilleDeja(unFromageId);
  }

  public int capaciteMaximale() {
    return capaciteMaximale;
  }

  public double temperatureCible() {
    return temperatureCible;
  }

  public double humiditeCible() {
    return humiditeCible;
  }

  public boolean estPleine(FromageId unFromageId) {
    boolean onPlaceLeMemeFromage = fromagesEnCave.contains(unFromageId);
    if  (onPlaceLeMemeFromage)
      return false;
    return fromagesEnCave.size() == this.capaciteMaximale();
  }

  private void setId(CaveAffinageId id) {
    this.verifieArgumentNonNull(id, "L'identifiant de la cave est requis.");
    this.id = id;
  }

  private void setCapaciteMaximale(int capaciteMaximale) {
    this.verifieArgumentNonNull(capaciteMaximale, "La capacite maximale de la cave d'affinage est requise.");
    this.capaciteMaximale = capaciteMaximale;
  }

  private void setTemperatureCible(double temperatureCible) {
    this.verifieArgumentNonNull(temperatureCible, "La temperature cible de la cave est requise.");
    this.temperatureCible = temperatureCible;
  }

  private void setHumiditeCible(double humiditeCible) {
    this.verifieArgumentNonNull(humiditeCible,  "La humidite cible de la cave est requise.");
    this.humiditeCible = humiditeCible;
  }

  @Override
  public boolean equals(Object unObjet) {
    if (unObjet == null) return false;
    if (getClass() != unObjet.getClass()) return false;
    if (this == unObjet) return true;

    return id.equals(((CaveAffinage) unObjet).id);
  }
}
