package com.smgueye.affinage_fromage.domain.model;

import com.smgueye.affinage_fromage.commun.ObjetValeur;
import com.smgueye.affinage_fromage.domain.model.soins.TypeDeSoinAffinage;

import java.util.Objects;
import java.util.Set;

public class Famille extends ObjetValeur {
  private String nom;
  private PlageAffinage plageAffinage;
  private Set<TypeDeSoinAffinage> soinsObligatoires;

  public Famille(String nom, PlageAffinage plageAffinage, Set<TypeDeSoinAffinage> soinsObligatoires) {
    setNom(nom);
    setPlageAffinage(plageAffinage);
    setSoins(soinsObligatoires);
  }

  public Famille(Famille famille) {
    this(famille.nom(), famille.plageAffinage(), famille.soinsObligatoires());
  }

  public String nom() {
    return nom;
  }

  public PlageAffinage plageAffinage() {
    return plageAffinage;
  }

  public Set<TypeDeSoinAffinage> soinsObligatoires() {
    return soinsObligatoires;
  }

  private void setNom(String nom) {
    this.verifieArgumentNonNullNiVide(nom, "Le nom de la famille est requis.");
    this.nom = nom;
  }
  
  private void setSoins(Set<TypeDeSoinAffinage> soinsObligatoires) {
    this.verifieArgumentEstNonNulOuVide(soinsObligatoires, "La liste des soins a promulger doit etre fournis.");
    this.soinsObligatoires = soinsObligatoires;
  }

  private void setPlageAffinage(PlageAffinage plageAffinage) {
    this.verifieArgumentNonNull(plageAffinage, "La plage d'affinage de la famille est requise.");
    this.plageAffinage = plageAffinage;
  }
  @Override
  public boolean equals(Object unObjet) {
    if (unObjet == this) return true;
    if (unObjet == null) return false;
    if (this.getClass() != unObjet.getClass()) return false;

    Famille uneFamille = (Famille) unObjet;
    return Objects.equals(nom, uneFamille.nom()) &&
      plageAffinage.equals(uneFamille.plageAffinage()) &&
      soinsObligatoires.equals(uneFamille.soinsObligatoires);
  }
}
