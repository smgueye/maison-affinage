package com.smgueye.affinage_fromage.domain.etat;

import com.smgueye.affinage_fromage.commun.exceptions.NotImplementedException;
import com.smgueye.affinage_fromage.domain.fromage.Fromage;

public class EtatRetire implements Etat {
  @Override
  public void maturation(Fromage fromage) {
    throw new NotImplementedException();
  }

  @Override
  public void bloquer(Fromage fromage) {
    throw new NotImplementedException();
  }

  @Override
  public void pret(Fromage fromage) {
    throw new NotImplementedException();
  }

  @Override
  public void retirer(Fromage fromage) {
    throw new NotImplementedException();
  }
}
