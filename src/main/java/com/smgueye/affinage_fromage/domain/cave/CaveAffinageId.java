package com.smgueye.affinage_fromage.domain.cave;

import com.smgueye.affinage_fromage.commun.ObjetValeur;

import java.util.UUID;

public class CaveAffinageId extends ObjetValeur {
  private UUID id;

  public CaveAffinageId(UUID id) {
    setId(id);
  }
  
  public CaveAffinageId(CaveAffinageId caveAffinageId) {
    this(caveAffinageId.id());
  }
  
  public UUID id() {
    return id;
  }

  private void setId(UUID id) {
    this.verifieArgumentNonNull(id, "L'identifiant UUID de la cave est requis.");
    this.id = id;
  }

  @Override
  public boolean equals(Object unObjet) {
    if (unObjet == null) return false;
    if (unObjet == this) return true;
    if (unObjet.getClass() != this.getClass()) return false;
    return this.id == ((CaveAffinageId) unObjet).id();
  }
}
