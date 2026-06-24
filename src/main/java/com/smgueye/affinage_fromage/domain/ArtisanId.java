package com.smgueye.affinage_fromage.domain;

import com.smgueye.affinage_fromage.common.ValueObject;

import java.util.UUID;

public class ArtisanId extends ValueObject {
  private UUID id;

  public ArtisanId(UUID id) {
    this.id = id;
  }

  public ArtisanId(ArtisanId artisanId) {
    this(artisanId.id());
  }

  private UUID id() {
    return id;
  }

  @Override
  public boolean equals(Object unObjet) {
    if (unObjet == null) return false;
    if (unObjet == this) return true;
    if (unObjet.getClass() != this.getClass()) return false;
    return this.id == ((ArtisanId) unObjet).id();
  }
}
