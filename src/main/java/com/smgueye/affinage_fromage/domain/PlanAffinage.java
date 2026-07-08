package com.smgueye.affinage_fromage.domain;

import com.smgueye.affinage_fromage.commun.ObjetValeur;
import com.smgueye.affinage_fromage.domain.soins.SoinAffinage;

// import java.util.Objects;
import java.util.Set;

public class PlanAffinage extends ObjetValeur {
  private final Set<SoinAffinage> soinAffinages;

  public PlanAffinage(Set<SoinAffinage> listeDeSoins) {
    this.verifieArgumentEstNonNulOuVide(listeDeSoins, "L'identifiant de la cave ne peut pas être nul");
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
