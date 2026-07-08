package com.smgueye.affinage_fromage.domain;

import com.smgueye.affinage_fromage.commun.ObjetValeur;

import java.time.LocalDate;

public class PeriodeDeMaturation extends ObjetValeur {
  private LocalDate debut;
  private LocalDate fin;

  public PeriodeDeMaturation(LocalDate debut, LocalDate fin) {
    this.verifieArgumentIntervalDeDateValide(debut, fin, "La date de debut de maturation doit preceder la date de fin.");

    this.setDebut(debut);
    this.setFin(fin);
  }

  public PeriodeDeMaturation(PeriodeDeMaturation periodeDeMaturation) {
    this(periodeDeMaturation.debut(), periodeDeMaturation.fin());
  }

  public LocalDate debut() {
    return debut;
  }

  public LocalDate fin() {
    return fin;
  }

  public boolean chevauche(PeriodeDeMaturation periodeDeMaturation) {
    return !neChevauchePas(periodeDeMaturation);
  }

  public boolean neChevauchePas(PeriodeDeMaturation unePeriode) {
    return debut().isAfter(unePeriode.fin()) || fin().isBefore(unePeriode.debut());
  }

  private void setDebut(LocalDate debut) {
    this.verifieArgumentNonNull(debut, "Le début de la période de maturation est requise.");
    this.debut = debut;
  }

  private void setFin(LocalDate fin) {
    this.verifieArgumentNonNull(fin, "La fin de la période de maturation est requise.");
    this.fin = fin;
  }

  @Override
  public boolean equals(Object unObjet) {
    if(unObjet == null) return false;
    if (this == unObjet) return true;
    if (getClass() != unObjet.getClass()) return false;

    PeriodeDeMaturation unPeriode = (PeriodeDeMaturation) unObjet;
    return this.debut.equals(unPeriode.debut())  && this.fin.equals(unPeriode.fin());
  }
}
