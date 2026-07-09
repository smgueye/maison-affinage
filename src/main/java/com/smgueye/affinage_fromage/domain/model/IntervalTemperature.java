package com.smgueye.affinage_fromage.domain.model;

import com.smgueye.affinage_fromage.commun.ObjetValeur;

public class IntervalTemperature extends ObjetValeur {
  private double minimale;
  private double maximale;

  public IntervalTemperature(double minimale, double maximale) {
    if (minimale > maximale) {
      throw new IllegalArgumentException("La temperature minimale ne peut être supérieure a la temperature maximale.");
    }
    setMinimale(minimale);
    setMaximale(maximale);
  }

  public IntervalTemperature(IntervalTemperature unIntervalDeTemperature) {
    this(unIntervalDeTemperature.minimale, unIntervalDeTemperature.maximale);
  }

  public double minimale() {
    return minimale;
  }

  public double maximale() {
    return maximale;
  }

  private void setMinimale(double leMinimum) {
    this.verifieArgumentNonNull(leMinimum, "La température minimale est requise.");
    this.minimale = leMinimum;
  }

  private void setMaximale(double leMaximum) {
    this.verifieArgumentNonNull(leMaximum, "La température maximale est requise.");
    this.maximale = leMaximum;
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

  public boolean inclutPas(double temperatureCible) {
    return temperatureCible > maximale() || temperatureCible < minimale();
  }
}
