package com.smgueye.affinage_fromage.domain;

import com.smgueye.affinage_fromage.common.ValueObject;
import lombok.Getter;

@Getter
public class Poids extends ValueObject {
  private final double poids;

  public Poids(double poids) {
    this.poids = poids;
  }
}
