package com.smgueye.affinage_fromage.domain;

import com.smgueye.affinage_fromage.commun.ObjetValeur;

public class PlageAffinage extends ObjetValeur {
  private IntervalTemperature temperature;
  private IntervalHumidite humidite;

  public PlageAffinage(IntervalTemperature temperature, IntervalHumidite humidite) {
    setTemperature(temperature);
    setHumidite(humidite);
  }

  public PlageAffinage(PlageAffinage plageAffinage) {
    this(plageAffinage.temperature(), plageAffinage.humidite());
  }

  public IntervalTemperature temperature() {
    return temperature;
  }

  public IntervalHumidite humidite() {
    return humidite;
  }

  private void setHumidite(IntervalHumidite humidite) {
    this.verifieArgumentNonNull(humidite, "L'humidité de la plage d'affinage est requise.");
    this.humidite = humidite;
  }

  private void setTemperature(IntervalTemperature temperature) {
    this.verifieArgumentNonNull(temperature, "La temperature de la plage d'affinage est requise.");
    this.temperature = temperature;
  }

  public boolean equals(Object unObjet) {
    if (unObjet == null) return false;
    if (unObjet == this) return true;
    if (this.getClass() != unObjet.getClass()) return false;

    PlageAffinage unePlageAffinage = (PlageAffinage) unObjet;
    return this.temperature() == unePlageAffinage.temperature() &&
      this.humidite() == unePlageAffinage.humidite();
  }
}
