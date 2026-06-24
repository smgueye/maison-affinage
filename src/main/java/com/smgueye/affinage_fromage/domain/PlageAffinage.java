package com.smgueye.affinage_fromage.domain;

import com.smgueye.affinage_fromage.common.ValueObject;
import lombok.Getter;

@Getter
public class PlageAffinage extends ValueObject {
  private final IntervalTemperature temperature;
  private final IntervalHumidite humidite;

  public PlageAffinage(IntervalTemperature temperature, IntervalHumidite humidite) {
    this.temperature = temperature;
    this.humidite = humidite;
  }
}
