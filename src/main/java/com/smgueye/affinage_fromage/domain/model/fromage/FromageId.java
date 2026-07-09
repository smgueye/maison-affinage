package com.smgueye.affinage_fromage.domain.model.fromage;

import com.smgueye.affinage_fromage.commun.ObjetValeur;

import java.util.UUID;

public class FromageId extends ObjetValeur {

  private UUID id;

  public FromageId(UUID id) {
    setId(id);
  }

  private void setId(UUID id) {
    this.verifieArgumentNonNull(id, "L'identifiant du fromage est requis.");
    this.id = id;
  }

  public FromageId(FromageId fromageId) {
    this(fromageId.id());
  }

  private UUID id() {
    return id;
  }

  @Override
  public boolean equals(Object unObjet) {
    if (unObjet == null) return false;
    if (unObjet == this) return true;
    if (unObjet.getClass() != this.getClass()) return false;
    return this.id == ((FromageId) unObjet).id();
  }
}
