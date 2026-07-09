package com.smgueye.affinage_fromage.commandes;

import com.smgueye.affinage_fromage.domain.model.AffineurId;
import com.smgueye.affinage_fromage.domain.model.fromage.FromageId;
import com.smgueye.affinage_fromage.domain.model.soins.TypeDeSoinAffinage;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class AppliquerSoinCommande {
  private final FromageId fromageId;
  private final LocalDate date;
  private final String observations;
  private final TypeDeSoinAffinage  typeDeSoin;
  private final AffineurId realisePar;

  public AppliquerSoinCommande(FromageId fromageId, LocalDate date, String observations, TypeDeSoinAffinage typeDeSoin, AffineurId realisePar) {
    this.fromageId = fromageId;
    this.date = date;
    this.observations = observations;
    this.typeDeSoin = typeDeSoin;
    this.realisePar = realisePar;
  }
}
