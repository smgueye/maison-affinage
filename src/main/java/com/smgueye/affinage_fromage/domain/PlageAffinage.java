package com.smgueye.affinage_fromage.domain;

import com.smgueye.affinage_fromage.common.ValueObject;
import lombok.Getter;

public class PlageAffinage extends ValueObject {
  private final IntervalTemperature temperature;
  private final IntervalHumidite humidite;

  public PlageAffinage(IntervalTemperature temperature, IntervalHumidite humidite) {
    this.temperature = temperature;
    this.humidite = humidite;
  }

  public PlageAffinage(PlageAffinage plageAffinage) {
    this(plageAffinage.temperature(), plageAffinage.humidite());
  }

  private IntervalTemperature temperature() {
    return temperature;
  }

  private IntervalHumidite humidite() {
    return humidite;
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
