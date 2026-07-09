package com.smgueye.affinage_fromage.domain.model;

import com.smgueye.affinage_fromage.commun.ObjetValeur;

import java.util.UUID;

public class AffineurId extends ObjetValeur {
  private UUID id;

  public AffineurId(UUID id) {
    setId(id);
  }

  public AffineurId(AffineurId artisanId) {
    this(artisanId.id());
  }

  public UUID id() {
    return id;
  }

  private void setId(UUID id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object unObjet) {
    if (unObjet == null) return false;
    if (unObjet == this) return true;
    if (unObjet.getClass() != this.getClass()) return false;
    return this.id == ((AffineurId) unObjet).id();
  }
}
