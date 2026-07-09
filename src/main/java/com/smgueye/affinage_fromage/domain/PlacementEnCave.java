package com.smgueye.affinage_fromage.domain;

import com.smgueye.affinage_fromage.domain.model.PeriodeDeMaturation;
import com.smgueye.affinage_fromage.domain.model.cave.CaveAffinage;
import com.smgueye.affinage_fromage.domain.model.fromage.Fromage;

public class PlacementEnCave {

  public void placerLeFromage(CaveAffinage cave, Fromage fromage, PeriodeDeMaturation periodeDeMaturation) {
    cave.placeUnFromage(fromage.id(), fromage.plageAffinage());
    fromage.marquerCommeEtantEnMaturation(cave.id(), periodeDeMaturation);
  }
}
