package com.smgueye.affinage_fromage.commun;

import com.smgueye.affinage_fromage.domain.cave.CaveAffinage;
import com.smgueye.affinage_fromage.domain.cave.CaveAffinageId;

import java.util.UUID;

public class CaveAffinageEntiteBuilder {
  private CaveAffinageId id;
  private int capaciteMaximale;
  private double temperatureCible;
  private double humiditeCible;

  private CaveAffinageEntiteBuilder() {}

  private CaveAffinageEntiteBuilder(CaveAffinageId id, int capaciteMaximale, double temperatureCible, double humiditeCible) {
    this.id = id;
    this.capaciteMaximale = capaciteMaximale;
    this.temperatureCible = temperatureCible;
    this.humiditeCible = humiditeCible;
  }

  public static CaveAffinageEntiteBuilder uneCave() {
    return new CaveAffinageEntiteBuilder(new CaveAffinageId(UUID.randomUUID()), 1, 12, 95);
  }

  public CaveAffinageEntiteBuilder avecId(CaveAffinageId uneIdCaveAffinage) {
    this.id = uneIdCaveAffinage;
    return this;
  }

  public CaveAffinageEntiteBuilder avecCapaciteMaximale(int capaciteMaximale) {
    this.capaciteMaximale = capaciteMaximale;
    return this;
  }

  public CaveAffinageEntiteBuilder avecTemperatureCible(double temperatureCible) {
    this.temperatureCible = temperatureCible;
    return this;
  }

  public CaveAffinageEntiteBuilder avecHumiditeCible(double humiditeCible) {
    this.humiditeCible = humiditeCible;
    return this;
  }

  public CaveAffinage construction() {
    return new CaveAffinage(id, capaciteMaximale, temperatureCible, humiditeCible);
  }
}
