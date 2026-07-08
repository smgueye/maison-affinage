package com.smgueye.affinage_fromage.domain;

import com.smgueye.affinage_fromage.commun.ObjetValeur;

import java.util.UUID;

public class ArtisanId extends ObjetValeur {
  private UUID id;

  public ArtisanId(UUID id) {
    setId(id);
  }

  public ArtisanId(ArtisanId artisanId) {
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
    return this.id == ((ArtisanId) unObjet).id();
  }
}
