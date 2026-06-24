package com.smgueye.affinage_fromage.domain.cave;

import com.smgueye.affinage_fromage.domain.fromage.FromageId;
import lombok.Getter;

import java.util.UUID;

public class CaveAffinageId {
  private final UUID id;

  public CaveAffinageId(UUID id) {
    this.id = id;
  }
  
  public CaveAffinageId(CaveAffinageId caveAffinageId) {
    this(caveAffinageId.id());
  }
  
  public UUID id() {
    return id;
  }

  @Override
  public boolean equals(Object unObjet) {
    if (unObjet == null) return false;
    if (unObjet == this) return true;
    if (unObjet.getClass() != this.getClass()) return false;
    return this.id == ((CaveAffinageId) unObjet).id();
  }
}
