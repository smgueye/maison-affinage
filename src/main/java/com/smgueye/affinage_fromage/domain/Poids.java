package com.smgueye.affinage_fromage.domain;

import com.smgueye.affinage_fromage.commun.ObjetValeur;

public class Poids extends ObjetValeur {
  private final double poids;

  public Poids(double poids) {
    this.verifieArgumentNonNull(poids, "Le poids est requis.");
    this.poids = poids;
  }
}
