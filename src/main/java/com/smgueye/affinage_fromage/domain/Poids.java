package com.smgueye.affinage_fromage.domain;

import com.smgueye.affinage_fromage.commun.ValueObject;
import lombok.Getter;

public class Poids extends ValueObject {
  private final double poids;

  public Poids(double poids) {
    this.verifieArgumentNonNull(poids, "Le poids est requis.");
    this.poids = poids;
  }
}
