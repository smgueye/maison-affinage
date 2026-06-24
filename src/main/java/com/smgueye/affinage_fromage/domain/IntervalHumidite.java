package com.smgueye.affinage_fromage.domain;

import com.smgueye.affinage_fromage.common.ValueObject;
import lombok.Getter;

import java.util.UUID;

public class IntervalHumidite extends ValueObject {

  private final double minimum;
  private final double maximum;

  public IntervalHumidite(double minimum, double maximum) {
    this.minimum = minimum;
    this.maximum = maximum;
  }

  public IntervalHumidite(IntervalHumidite intervalHumidite) {
    this(intervalHumidite.minimum(), intervalHumidite.maximum());
  }

  private double minimum() {
    return minimum;
  }

  private double maximum() {
    return maximum;
  }

  @Override
  public boolean equals(Object unObjet) {
    if (unObjet == null) return false;
    if (unObjet == this) return true;
    if (unObjet.getClass() != this.getClass()) return false;

    IntervalHumidite unIntervalHumidite = (IntervalHumidite) unObjet;
    return this.minimum  == unIntervalHumidite.minimum() &&
      this.maximum == unIntervalHumidite.maximum();
  }
}
