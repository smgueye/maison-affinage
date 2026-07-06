package com.smgueye.affinage_fromage.domain.fromage;

import com.smgueye.affinage_fromage.commun.Entity;
import com.smgueye.affinage_fromage.commun.exceptions.CapaciteMaximaleDepasseeException;
import com.smgueye.affinage_fromage.commun.exceptions.IncompatibiliteAvecCaveException;
import com.smgueye.affinage_fromage.commun.exceptions.IncompatibiliteAvecLaPeriodeDurantLePlacement;
import com.smgueye.affinage_fromage.domain.*;
import com.smgueye.affinage_fromage.domain.cave.CaveAffinage;
import com.smgueye.affinage_fromage.domain.etat.AucunEtat;
import com.smgueye.affinage_fromage.domain.etat.Etat;
import com.smgueye.affinage_fromage.domain.etat.EtatEnMaturation;
import com.smgueye.affinage_fromage.domain.etat.EtatRecu;
import com.smgueye.affinage_fromage.domain.Famille;

import java.time.LocalDate;

import static com.smgueye.affinage_fromage.commun.messages.Message.HUMIDITE_INCOMPATIBLE_AVEC_LA_CAVE;
import static com.smgueye.affinage_fromage.commun.messages.Message.TEMPERATURE_INCOMPATIBLE_AVEC_LA_CAVE;

public class Fromage extends Entity {

  private FromageId id;
  private String name;
  private Famille famille;
  private Poids poids;
  private LocalDate dateDeReception;
  private Etat etat;
  private ArtisanId artisanId;
  private CaveAffinage caveAffinage;
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

  // TODO : Transaction
  public void placeEnCave(CaveAffinage caveAffinage, PeriodeDeMaturation periodeDeMaturation)
    throws CapaciteMaximaleDepasseeException, IncompatibiliteAvecCaveException {
    this.verifierConditionsPourPlacerUnFromage(caveAffinage, periodeDeMaturation);

    this.caveAffinage = caveAffinage;
    this.periodeDeMaturation = periodeDeMaturation;
    this.caveAffinage.place(this);
    this.etat = new EtatEnMaturation();
  }

  // TODO -- Transaction
  public void recu() {
    this.etat = new EtatRecu();
  }

  public boolean estRecu() {
    return this.etat.getClass().equals(EtatRecu.class);
  }

  public boolean estEnMaturation() {
    return  this.etat.getClass().equals(EtatEnMaturation.class);
  }

  public boolean estPlaceDansLaCave(CaveAffinage laCave) {
    return this.caveAffinage.equals(laCave);
  }

  private void verifierConditionsPourPlacerUnFromage(CaveAffinage cave,
                                                     PeriodeDeMaturation periodeDeMaturation)
    throws CapaciteMaximaleDepasseeException, IncompatibiliteAvecCaveException {
    if (laPeriodeEstIncompatible(cave, periodeDeMaturation))
      throw new IncompatibiliteAvecLaPeriodeDurantLePlacement();

    if (cave.estPleine(this.id()))
      throw new CapaciteMaximaleDepasseeException();

    PlageAffinage laPlageAffinage = plageAffinage();
    IntervalHumidite intervalHumidite = laPlageAffinage.humidite();
    if (intervalHumidite.neContientPas(cave.humiditeCible()))
      throw new IncompatibiliteAvecCaveException(HUMIDITE_INCOMPATIBLE_AVEC_LA_CAVE);

    IntervalTemperature intervalTemperature = laPlageAffinage.temperature();
    if (intervalTemperature.neContientPas(cave.temperatureCible()))
      throw new IncompatibiliteAvecCaveException(TEMPERATURE_INCOMPATIBLE_AVEC_LA_CAVE);
  }

  private boolean laPeriodeEstCompatible(CaveAffinage cave, PeriodeDeMaturation nouvellePeriode) {
    if (periodeDeMaturation == null)
      return true;

    if (cave.accueillePasEncore(this.id()))
      return true;

    if (this.periodeDeMaturation.contient(nouvellePeriode))
      return false;

    return true;
  }

  private boolean laPeriodeEstIncompatible(CaveAffinage cave, PeriodeDeMaturation nouvellePeriode) {
    return !laPeriodeEstCompatible(cave, nouvellePeriode);
  }

  @Override
  public boolean equals(Object unObjet) {
    if (unObjet == null) return false;
    if (this == unObjet) return true;
    if (this.getClass() != unObjet.getClass()) return false;

    return id.equals(((Fromage) unObjet).id);
  }
}
