package com.smgueye.affinage_fromage.domain;

import com.smgueye.affinage_fromage.common.ValueObject;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FromageId extends ValueObject {

  private final UUID id;

  public FromageId(UUID id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object unObjet) {
    if (unObjet == null) return false;
    if (unObjet == this) return true;
    if (unObjet.getClass() != this.getClass()) return false;
    return this.id == ((FromageId) unObjet).getId();
  }
}
