package com.smgueye.affinage_fromage.domain;

import com.smgueye.affinage_fromage.common.ValueObject;
import lombok.Getter;

@Getter
public class IntervalTemperature extends ValueObject {
  private final float minimale;
  private final float maximale;

  public IntervalTemperature(float minimale, float maximale) {
    this.minimale = minimale;
    this.maximale = maximale;
  }
}
