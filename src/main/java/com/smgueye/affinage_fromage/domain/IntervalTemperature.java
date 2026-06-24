package com.smgueye.affinage_fromage.domain;

import com.smgueye.affinage_fromage.common.ValueObject;
import lombok.Getter;

public class IntervalTemperature extends ValueObject {
  private final double minimale;
  private final double maximale;

  public IntervalTemperature(double minimale, double maximale) {
    this.minimale = minimale;
    this.maximale = maximale;
  }

  public IntervalTemperature(IntervalTemperature unIntervalDeTemperature) {
    this(unIntervalDeTemperature.minimale, unIntervalDeTemperature.maximale);
  }

  private double minimale() {
    return minimale;
  }

  private double maximale() {
    return maximale;
  }

  @Override
  public boolean equals(Object unObjet) {
    if (unObjet == null) return false;
    if (unObjet == this) return true;
    if (unObjet.getClass() != this.getClass()) return false;
    IntervalTemperature unIntervalDeTemperature = (IntervalTemperature) unObjet;
    return this.minimale == unIntervalDeTemperature.minimale() &&
      this.maximale == unIntervalDeTemperature.maximale();
  }
}
