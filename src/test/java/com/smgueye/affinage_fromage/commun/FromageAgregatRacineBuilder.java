package com.smgueye.affinage_fromage.commun;

import com.smgueye.affinage_fromage.domain.*;
import com.smgueye.affinage_fromage.domain.fromage.Fromage;
import com.smgueye.affinage_fromage.domain.fromage.FromageId;
import com.smgueye.affinage_fromage.domain.Famille;
import com.smgueye.affinage_fromage.domain.soins.TypeDeSoinAffinage;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class FromageAgregatRacineBuilder {
  private FromageId fromageId = null;
  private ArtisanId artisanId = null;
  private String nom = null;
  private Poids poids = new Poids(100);
  private LocalDate dateDeReception = null;
  private PeriodeDeMaturation periodeDeMaturation = null;
  private Famille famille  = null;
  private PlageAffinage plageAffinage = null;

  private FromageAgregatRacineBuilder() {}

  private FromageAgregatRacineBuilder(FromageId sonFromageId,
                                      ArtisanId sonArtisanId,
                                      String sonNom,
                                      Poids sonPoids,
                                      LocalDate saDateDeReception,
                                      Famille saFamille
                                      // PlageAffinage saPlageAffinage
  ) {
    this.fromageId = sonFromageId;
    this.artisanId = sonArtisanId;
    this.nom = sonNom;
    this.poids = sonPoids;
    this.dateDeReception = saDateDeReception;
    this.famille = saFamille;
    // this.plageAffinage = saPlageAffinage;
  }

  public static FromageAgregatRacineBuilder unBonFromage() {
    return new FromageAgregatRacineBuilder(
      new FromageId(UUID.randomUUID()),
      new ArtisanId(UUID.randomUUID()),
      "Nom du fromage valide",
      new Poids(100),
      LocalDate.now(),
      new Famille("BLEU",
        new PlageAffinage(new IntervalTemperature(12, 15), new IntervalHumidite(90, 95)),
        Set.of(
          TypeDeSoinAffinage.PIQUAGE,
          TypeDeSoinAffinage.RETOURNEMENT,
          TypeDeSoinAffinage.CONTROLE_OLFACTIF)));
  }

  public static FromageAgregatRacineBuilder unFromageVide() {
    return new FromageAgregatRacineBuilder();
  }

  public FromageAgregatRacineBuilder avecId(FromageId sonFromageId) {
    this.fromageId = sonFromageId;
    return this;
  }

  public FromageAgregatRacineBuilder avecArtisanId(ArtisanId sonArtisanId) {
    this.artisanId = sonArtisanId;
    return this;
  }

  public FromageAgregatRacineBuilder avecNom(String sonNom) {
    this.nom = sonNom;
    return this;
  }

  public FromageAgregatRacineBuilder avecPoids(Poids sonPoids) {
    this.poids = sonPoids;
    return this;
  }

  public FromageAgregatRacineBuilder avecFamille(Famille saFamille) {
    this.famille = saFamille;
    return this;
  }

  public FromageAgregatRacineBuilder avecDateDeReception(LocalDate uneDateDeReception) {
    this.dateDeReception = uneDateDeReception;
    return this;
  }

  public FromageAgregatRacineBuilder avecPlanAffinage(PlageAffinage plageAffinage) {
    this.famille = new Famille(famille.nom(), plageAffinage, famille.soinsObligatoires());
    return this;
  }

  public FromageAgregatRacineBuilder avecPeriodeDeMaturation(PeriodeDeMaturation periodeDeMaturation) {
    this.periodeDeMaturation = periodeDeMaturation;
    return this;
  }

  public static FromageAgregatRacineBuilder reConstruire(Fromage fromage) {
    return new FromageAgregatRacineBuilder(
      fromage.id(),
      fromage.artisanId(),
      fromage.nom(),
      fromage.poids(),
      fromage.dateReception(),
      fromage.famille());
  }

  public Fromage construction() {
    return new Fromage(fromageId, nom, poids, dateDeReception, famille, artisanId);
  }
}
