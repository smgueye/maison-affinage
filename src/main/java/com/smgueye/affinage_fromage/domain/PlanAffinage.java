package com.smgueye.affinage_fromage.domain;

import com.smgueye.affinage_fromage.common.ValueObject;

import java.util.Set;

public class PlanAffinage extends ValueObject {
  private final Set<SoinAffinage> soinAffinages;

  public PlanAffinage(Set<SoinAffinage> listeDeSoins) {
    this.soinAffinages = listeDeSoins;
  }

  public PlanAffinage(PlanAffinage unPlan) {
    this(unPlan.soinAffinages());
  }

  private Set<SoinAffinage> soinAffinages() {
    return this.soinAffinages;
  }

  // TODO
  // @Override
  // public boolean equals(Object unObjet) {}
}
