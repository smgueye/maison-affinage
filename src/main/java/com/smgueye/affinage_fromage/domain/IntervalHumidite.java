package com.smgueye.affinage_fromage.domain;

import com.smgueye.affinage_fromage.common.ValueObject;
import lombok.Getter;

@Getter
public class IntervalHumidite extends ValueObject {

  private final float minimum;
  private final float maximum;

  public IntervalHumidite(float minimum, float maximum) {
    this.minimum = minimum;
    this.maximum = maximum;
  }
}
