package com.smgueye.affinage_fromage.commun;

import com.smgueye.affinage_fromage.commandes.PlacerEnCaveCommande;
import com.smgueye.affinage_fromage.domain.PeriodeDeMaturation;
import com.smgueye.affinage_fromage.domain.cave.CaveAffinage;
import com.smgueye.affinage_fromage.domain.fromage.Fromage;

public class PlacerEnCaveCommandeBuilder {
  private CaveAffinage cave;
  private Fromage fromage;
  private PeriodeDeMaturation periodeDeMaturation;

  private PlacerEnCaveCommandeBuilder(CaveAffinage cave, Fromage fromage, PeriodeDeMaturation periode) {
    this.cave = cave;
    this.fromage = fromage;
    this.periodeDeMaturation = periode;
  }

  public static PlacerEnCaveCommandeBuilder uneCommandeDePlacement() {
    return new PlacerEnCaveCommandeBuilder(
      CaveAffinageEntiteBuilder.uneCave().construction(),
      FromageAgregatRacineBuilder.unBonFromage().construction(),
      new PeriodeDeMaturation(TimeTraveler.Moment().aujourdHui(), TimeTraveler.Moment().dansUneSemaine()));
  }

  public PlacerEnCaveCommandeBuilder avecCave(CaveAffinage cave) {
    this.cave = cave;
    return  this;
  }

  public PlacerEnCaveCommandeBuilder avecFromage(Fromage fromage) {
    this.fromage = fromage;
    return  this;
  }

  public PlacerEnCaveCommandeBuilder avecPeriodeDeMaturation(PeriodeDeMaturation periodeDeMaturation) {
    this.periodeDeMaturation = periodeDeMaturation;
    return this;
  }

  public PlacerEnCaveCommande construire() {
    return new PlacerEnCaveCommande(cave, fromage, periodeDeMaturation);
  }
}
