package com.smgueye.affinage_fromage.domain;

import com.smgueye.affinage_fromage.commun.ObjetValeur;

public class IntervalHumidite extends ObjetValeur {

  private double minimum;
  private double maximum;

  public IntervalHumidite(double minimum, double maximum) {
    if (minimum > maximum) {
      throw new IllegalArgumentException("L'humidité minimale ne peut être supérieure a la temperature maximale.");
    }
    setMinimum(minimum);
    setMaximum(maximum);
  }

  public IntervalHumidite(IntervalHumidite intervalHumidite) {
    this(intervalHumidite.minimum(), intervalHumidite.maximum());
  }

  public double minimum() {
    return minimum;
  }

  public double maximum() {
    return maximum;
  }

  public boolean neContientPas(double humiditeCible) {
    return humiditeCible > maximum() || humiditeCible < minimum();
  }

  private void setMinimum(double leMinimum) {
    this.verifieArgumentNonNull(leMinimum, "L'humidité minimale est requise.");
    this.minimum = leMinimum;
  }

  private void setMaximum(double leMaximum) {
    this.verifieArgumentNonNull(leMaximum, "L'humidité maximale est requise.");
    this.maximum = leMaximum;
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
