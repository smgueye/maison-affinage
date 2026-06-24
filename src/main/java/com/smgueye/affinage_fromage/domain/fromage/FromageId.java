package com.smgueye.affinage_fromage.domain.fromage;

import com.smgueye.affinage_fromage.common.ValueObject;
import lombok.Getter;

import java.util.UUID;

public class FromageId extends ValueObject {

  private final UUID id;

  public FromageId(UUID id) {
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
