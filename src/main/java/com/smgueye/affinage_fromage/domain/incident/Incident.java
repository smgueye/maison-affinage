package com.smgueye.affinage_fromage.domain.incident;

import com.smgueye.affinage_fromage.common.ValueObject;

public class Incident extends ValueObject {
  private final TypeIncident type;
  private final String description;

  public Incident(TypeIncident type, String description) {
    this.type = type;
    this.description = description;
  }

  public Incident(Incident incident) {
    this(incident.type(), incident.description());
  }

  private TypeIncident type() {
    return type;
  }

  private String description() {
    return description;
  }

  @Override
  public boolean equals(Object unObjet) {
    if (unObjet == null) return false;
    if (unObjet == this) return true;
    if (unObjet.getClass() != this.getClass()) return false;

    Incident unIncident = (Incident) unObjet;
    return this.type == unIncident.type() &&
      unIncident.description().equals(this.description());

  }
}
