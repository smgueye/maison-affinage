package com.smgueye.affinage_fromage.domain.cave;

import com.smgueye.affinage_fromage.common.Entity;
import com.smgueye.affinage_fromage.common.exceptions.NotImplementedException;
import com.smgueye.affinage_fromage.domain.fromage.Fromage;

public class CaveAffinage extends Entity {
  private final CaveAffinageId id;

  public CaveAffinage(CaveAffinageId id) {
    this.id = id;
  }

  public boolean estCompatible(Fromage fromage) {
    throw new NotImplementedException();
  }
}
