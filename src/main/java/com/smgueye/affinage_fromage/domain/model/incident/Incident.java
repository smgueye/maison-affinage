package com.smgueye.affinage_fromage.domain.model.incident;

import com.smgueye.affinage_fromage.commun.ObjetValeur;

public class Incident extends ObjetValeur {
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
