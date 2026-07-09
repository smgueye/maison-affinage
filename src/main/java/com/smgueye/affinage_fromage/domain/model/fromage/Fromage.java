package com.smgueye.affinage_fromage.domain.model.fromage;

import com.smgueye.affinage_fromage.commun.Entity;
import com.smgueye.affinage_fromage.commun.exceptions.CapaciteMaximaleDepasseeException;
import com.smgueye.affinage_fromage.commun.exceptions.ExceptionMetier;
import com.smgueye.affinage_fromage.commun.exceptions.IncompatibiliteAvecCaveException;
import com.smgueye.affinage_fromage.commun.exceptions.IncompatibiliteAvecLaPeriodeDurantLePlacement;
import com.smgueye.affinage_fromage.domain.model.*;
import com.smgueye.affinage_fromage.domain.model.cave.CaveAffinageId;
import com.smgueye.affinage_fromage.domain.model.etat.AucunEtat;
import com.smgueye.affinage_fromage.domain.model.etat.Etat;
import com.smgueye.affinage_fromage.domain.model.etat.EtatEnMaturation;
import com.smgueye.affinage_fromage.domain.model.etat.EtatRecu;

import java.time.LocalDate;

public class Fromage extends Entity {

  private FromageId id;
  private String name;
  private Famille famille;
  private Poids poids;
  private LocalDate dateDeReception;
  private Etat etat;
  private ArtisanId artisanId;
  private CaveAffinageId caveAffinageId;
  private PeriodeDeMaturation periodeDeMaturation;

  public Fromage(FromageId id,
                 String name,
                 Poids poids,
                 LocalDate date,
                 Famille famille,
                 ArtisanId artisanId) {
    this.setId(id);
    this.setName(name);
    this.setPoids(poids);
    this.setDateDeReception(date);
    this.setEtat(new AucunEtat());
    this.setFamille(famille);
    this.setArtisanId(artisanId);
  }

  protected void setId(FromageId fromageId) {
    this.verifieArgumentNonNull(fromageId, "L'identifiant du fromage est requis.");
    this.id = fromageId;
  }

  protected void setName(String name) {
    this.verifieArgumentNonNullNiVide(name, "Le nom du fromage est requis.");
    this.name = name;
  }

  protected void setPoids(Poids poids) {
    this.verifieArgumentNonNull(poids, "Le poids du fromage est requis.");
    this.poids = poids;
  }

  protected void setDateDeReception(LocalDate dateDeReception) {
    this.verifieArgumentNonNull(dateDeReception, "La date de reception du fromage est requise.");
    this.dateDeReception = dateDeReception;
  }
  
  protected void setEtat(Etat etat) {
    this.verifieArgumentNonNull(etat, "Le état du fromage est requis.");
    this.etat = etat;
  }
  
  protected void setFamille(Famille famille) {
    this.verifieArgumentNonNull(famille,  "La famille du fromage est requise.");
    this.famille = famille;
  }

  protected void setArtisanId(ArtisanId artisanId) {
    this.verifieArgumentNonNull(artisanId, "L'identifiant de l'artisan est requis.");
    this.artisanId = artisanId;
  }

  public FromageId id() {
    return id;
  }

  public Etat etat() {
    return etat;
  }

  public ArtisanId artisanId() {
    return artisanId;
  }

  public String nom() {
    return name;
  }

  public Poids poids() {
    return poids;
  }

  public LocalDate dateReception() {
    return dateDeReception;
  }

  public Famille famille() {
    return famille;
  }

  public PeriodeDeMaturation periodeDeMaturation() {
    return periodeDeMaturation;
  }

  public PlageAffinage plageAffinage() {
    return this.famille.plageAffinage();
  }

  // TODO -- Transaction
  public void confierAMaisonAffinage() {
    if (this.aUnEtat())
      throw new ExceptionMetier("Le fromage n'est pas dans l'état adéquat");
    marquerCommeRecu();
  }

  public void marquerCommeRecu() {
    this.etat = new EtatRecu();
  }

  // TODO -- Transaction
  public void marquerCommeEtantEnMaturation(CaveAffinageId caveId, PeriodeDeMaturation periodeDeMaturation) {
    verifierLePlacementSurLaPeriode(periodeDeMaturation);

    this.caveAffinageId = caveId;
    this.periodeDeMaturation = periodeDeMaturation;
    this.etat = new EtatEnMaturation();
  }

  private boolean aAucunEtat() {
    return this.etat.getClass().equals(AucunEtat.class);
  }

  private boolean aUnEtat() {
    return !this.aAucunEtat();
  }

  public boolean estRecu() {
    return this.etat.getClass().equals(EtatRecu.class);
  }

  public boolean estEnMaturation() {
    return  this.etat.getClass().equals(EtatEnMaturation.class);
  }

  public boolean estPlaceDansLaCave(CaveAffinageId caveId) {
    return caveAffinageId.equals(caveId);
  }

  private void verifierLePlacementSurLaPeriode(PeriodeDeMaturation nouvellePeriodeDeMaturation)
    throws CapaciteMaximaleDepasseeException, IncompatibiliteAvecCaveException {
    if (periodeDeMaturation == null)
      return;

    if (periodeDeMaturation.chevauche(nouvellePeriodeDeMaturation))
      throw new IncompatibiliteAvecLaPeriodeDurantLePlacement();
  }

  @Override
  public boolean equals(Object unObjet) {
    if (unObjet == null) return false;
    if (this == unObjet) return true;
    if (this.getClass() != unObjet.getClass()) return false;

    return id.equals(((Fromage) unObjet).id);
  }
}
