package com.smgueye.affinage_fromage;

import com.smgueye.affinage_fromage.domain.Fromage;
import com.smgueye.affinage_fromage.domain.FromageId;

import java.util.*;

public class MaisonAffinageFacade {
  private final Map<FromageId, Fromage> PAR_FROMAGE_ID = new HashMap<>();

  public void recoit(Fromage fromage) {
    fromage.recu();
    PAR_FROMAGE_ID.put(fromage.getId(), fromage);
  }

  public Optional<Fromage> fromageAvecId(FromageId fromageId) {
    return Optional.ofNullable(PAR_FROMAGE_ID.get(fromageId));
  }
}
