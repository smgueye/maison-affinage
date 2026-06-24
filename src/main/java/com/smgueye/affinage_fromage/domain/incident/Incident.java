package com.smgueye.affinage_fromage.domain.incident;

import com.smgueye.affinage_fromage.common.ValueObject;
import lombok.Getter;

@Getter
public class Incident extends ValueObject {
  private final TypeIncident type;
  private final String description;

  public Incident(TypeIncident type, String description) {
    this.type = type;
    this.description = description;
  }
}
