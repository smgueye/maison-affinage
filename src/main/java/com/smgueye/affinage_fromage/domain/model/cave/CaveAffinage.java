package com.smgueye.affinage_fromage.domain.model.cave;

import com.smgueye.affinage_fromage.commun.Entity;
import com.smgueye.affinage_fromage.commun.exceptions.CapaciteMaximaleDepasseeException;
import com.smgueye.affinage_fromage.commun.exceptions.IncompatibiliteAvecCaveException;
import com.smgueye.affinage_fromage.domain.model.IntervalHumidite;
import com.smgueye.affinage_fromage.domain.model.IntervalTemperature;
import com.smgueye.affinage_fromage.domain.model.PlageAffinage;
import com.smgueye.affinage_fromage.domain.model.fromage.FromageId;

import java.util.HashSet;
import java.util.Set;

import static com.smgueye.affinage_fromage.commun.messages.Message.HUMIDITE_INCOMPATIBLE_AVEC_LA_CAVE;
import static com.smgueye.affinage_fromage.commun.messages.Message.TEMPERATURE_INCOMPATIBLE_AVEC_LA_CAVE;

public class CaveAffinage extends Entity {
  private CaveAffinageId id;
  private int capaciteMaximale;
  private double temperatureCible;
  private double humiditeCible;
  private final Set<FromageId> fromages = new HashSet<>();

  public CaveAffinage(CaveAffinageId id, int capaciteMaximale, double temperatureCible, double humiditeCible) {
    setId(id);
    setCapaciteMaximale(capaciteMaximale);
    setTemperatureCible(temperatureCible);
    setHumiditeCible(humiditeCible);
  }

  public CaveAffinageId id() {
    return id;
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

  public int capaciteMaximale() {
    return capaciteMaximale;
  }

  public double temperatureCible() {
    return temperatureCible;
  }

  public double humiditeCible() {
    return humiditeCible;
  }

  public void placeUnFromage(FromageId fromageId, PlageAffinage  plageAffinage) {
    this.verifieLaCapacite(fromageId);
    this.verifieLaCompatibilite(plageAffinage);

    this.reserverUnePlace(fromageId);
  }

  private void verifieLaCapacite(FromageId fromageId) {
    if (this.estPleine(fromageId))
      throw new CapaciteMaximaleDepasseeException();
  }

  private void verifieLaCompatibilite(PlageAffinage plageAffinage) {
    IntervalHumidite intervalHumidite = plageAffinage.humidite();
    if (intervalHumidite.inclutPas(humiditeCible()))
      throw new IncompatibiliteAvecCaveException(HUMIDITE_INCOMPATIBLE_AVEC_LA_CAVE);

    IntervalTemperature intervalTemperature = plageAffinage.temperature();
    if (intervalTemperature.inclutPas(temperatureCible()))
      throw new IncompatibiliteAvecCaveException(TEMPERATURE_INCOMPATIBLE_AVEC_LA_CAVE);
  }

  private void reserverUnePlace(FromageId fromageId) {
    fromages.add(fromageId);
  }

  public boolean estPleine(FromageId unFromageId) {
    boolean onPlaceLeMemeFromage = accueilleDeja(unFromageId);
    if  (onPlaceLeMemeFromage)
      return false;
    return fromages.size() == this.capaciteMaximale();
  }

  public boolean accueilleDeja(FromageId unFromageId) {
    return fromages.contains(unFromageId);
  }

  @Override
  public boolean equals(Object unObjet) {
    if (unObjet == null) return false;
    if (getClass() != unObjet.getClass()) return false;
    if (this == unObjet) return true;

    return id.equals(((CaveAffinage) unObjet).id);
  }
}
