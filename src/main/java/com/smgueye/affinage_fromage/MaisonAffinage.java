package com.smgueye.affinage_fromage;

import com.smgueye.affinage_fromage.commandes.PlacerEnCaveCommande;
import com.smgueye.affinage_fromage.commun.exceptions.CapaciteMaximaleDepasseeException;
import com.smgueye.affinage_fromage.commun.exceptions.IncompatibiliteAvecCaveException;
import com.smgueye.affinage_fromage.domain.PeriodeDeMaturation;
import com.smgueye.affinage_fromage.domain.cave.CaveAffinage;
import com.smgueye.affinage_fromage.domain.fromage.Fromage;
import com.smgueye.affinage_fromage.domain.fromage.FromageId;

import java.util.*;

public class MaisonAffinage {
  private final Map<FromageId, Fromage> PAR_FROMAGE_ID = new HashMap<>();

  public void recoit(Fromage fromage) {
    fromage.recu();
    ajouter(fromage);
  }

  public Optional<Fromage> fromageAvecId(FromageId fromageId) {
    return Optional.ofNullable(PAR_FROMAGE_ID.get(fromageId));
  }

  public void placeEnCave(PlacerEnCaveCommande commande) throws CapaciteMaximaleDepasseeException, IncompatibiliteAvecCaveException {
    Fromage leFromage = commande.fromage();
    CaveAffinage laCave = commande.cave();
    PeriodeDeMaturation laPeriodeDeMaturation = commande.periodeDeMaturation();
    leFromage.placeEnCave(laCave, laPeriodeDeMaturation);
    ajouter(leFromage);
  }

  private void ajouter(Fromage fromage) {
    PAR_FROMAGE_ID.put(fromage.id(), fromage);
  }
}
