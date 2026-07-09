package com.smgueye.affinage_fromage.domain.model.etat;

import com.smgueye.affinage_fromage.commun.exceptions.NotImplementedException;
import com.smgueye.affinage_fromage.domain.model.fromage.Fromage;

public class EtatRecu implements Etat {
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
