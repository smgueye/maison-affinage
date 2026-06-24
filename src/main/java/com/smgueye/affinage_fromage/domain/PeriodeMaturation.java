package com.smgueye.affinage_fromage.domain;

import com.smgueye.affinage_fromage.common.ValueObject;
import lombok.Getter;

import java.util.Date;

public class PeriodeMaturation extends ValueObject {
  private final Date debut;
  private final Date fin;

  public PeriodeMaturation(Date debut, Date fin) {
    this.debut = debut;
    this.fin = fin;
  }

  public PeriodeMaturation(PeriodeMaturation periodeMaturation) {
    this(periodeMaturation.debut(), periodeMaturation.fin());
  }

  private Date debut() {
    return debut;
  }

  private Date fin() {
    return fin;
  }

  @Override
  public boolean equals(Object unObjet) {
    if(unObjet == null) return false;
    if (this == unObjet) return true;
    if (getClass() != unObjet.getClass()) return false;

    PeriodeMaturation unPeriode = (PeriodeMaturation) unObjet;
    return this.debut.equals(unPeriode.debut())  && this.fin.equals(unPeriode.fin());
  }
}
