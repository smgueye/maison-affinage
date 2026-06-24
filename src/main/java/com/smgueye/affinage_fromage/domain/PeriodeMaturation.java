package com.smgueye.affinage_fromage.domain;

import lombok.Getter;

import java.util.Date;

@Getter
public class PeriodeMaturation {
  private final Date debut;
  private final Date fin;

  public PeriodeMaturation(Date debut, Date fin) {
    this.debut = debut;
    this.fin = fin;
  }
}
