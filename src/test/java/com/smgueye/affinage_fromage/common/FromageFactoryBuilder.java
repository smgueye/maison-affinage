package com.smgueye.affinage_fromage.common;

import com.smgueye.affinage_fromage.domain.ArtisanId;
import com.smgueye.affinage_fromage.domain.Famille;
import com.smgueye.affinage_fromage.domain.Poids;
import com.smgueye.affinage_fromage.domain.fromage.Fromage;
import com.smgueye.affinage_fromage.domain.fromage.FromageId;

import java.time.LocalDate;
import java.util.UUID;

public class FromageFactoryBuilder {
  FromageId fromageId = null;
  ArtisanId artisanId = null;
  String nom = null;
  Poids poids = new Poids(100);
  LocalDate dateDeReception = null;
  Famille famille  = null;

  private FromageFactoryBuilder() {}

  private FromageFactoryBuilder(FromageId sonFromageId,
                                ArtisanId sonArtisanId,
                                String sonNom,
                                Poids sonPoids,
                                LocalDate saDateDeReception,
                                Famille saFamille) {
    this.fromageId = sonFromageId;
    this.artisanId = sonArtisanId;
    this.nom = sonNom;
    this.poids = sonPoids;
    this.dateDeReception = saDateDeReception;
    this.famille = saFamille;
  }

  public static FromageFactoryBuilder unValide() {
    return new FromageFactoryBuilder(
      new FromageId(UUID.randomUUID()),
      new ArtisanId(UUID.randomUUID()),
      "Nom du fromage valide",
      new Poids(100),
      LocalDate.now(),
      Famille.BLEU);
  }

  public static FromageFactoryBuilder unInValide() {
    return new FromageFactoryBuilder();
  }

  public FromageFactoryBuilder avecId(FromageId sonFromageId) {
    this.fromageId = sonFromageId;
    return this;
  }

  public FromageFactoryBuilder avecArtisanId(ArtisanId sonArtisanId) {
    this.artisanId = sonArtisanId;
    return this;
  }

  public FromageFactoryBuilder avecNom(String sonNom) {
    this.nom = sonNom;
    return this;
  }

  public FromageFactoryBuilder avecPoids(Poids sonPoids) {
    this.poids = sonPoids;
    return this;
  }

  public FromageFactoryBuilder avecFamille(Famille saFamille) {
    this.famille = saFamille;
    return this;
  }

  public Fromage construction() {
    return new Fromage(fromageId, nom, poids, dateDeReception, famille, artisanId);
  }

  public FromageFactoryBuilder avecDateDeReception(LocalDate uneDateDeReception) {
    this.dateDeReception = uneDateDeReception;
    return this;
  }
}
