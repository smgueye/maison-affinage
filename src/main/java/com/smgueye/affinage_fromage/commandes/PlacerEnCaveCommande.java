package com.smgueye.affinage_fromage.commandes;

import com.smgueye.affinage_fromage.domain.model.PeriodeDeMaturation;
import com.smgueye.affinage_fromage.domain.model.cave.CaveAffinage;
import com.smgueye.affinage_fromage.domain.model.fromage.Fromage;

public class PlacerEnCaveCommande {

  private final CaveAffinage cave;
  private final Fromage fromage;
  private final PeriodeDeMaturation periodeDeMaturation;

  public PlacerEnCaveCommande(CaveAffinage uneCave, Fromage unFromage, PeriodeDeMaturation periodeDeMaturation) {
    this.cave = uneCave;
    this.fromage = unFromage;
    this.periodeDeMaturation = periodeDeMaturation;
  }

  public CaveAffinage cave() {
    return cave;
  }

  public Fromage fromage() {
    return fromage;
  }

  public PeriodeDeMaturation periodeDeMaturation() {
    return periodeDeMaturation;
  }
}
