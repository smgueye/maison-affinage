package com.smgueye.affinage_fromage.domain.model.etat;


import com.smgueye.affinage_fromage.domain.model.fromage.Fromage;

public interface Etat {

  void maturation(Fromage fromage);
  void bloquer(Fromage fromage);
  void pret(Fromage fromage);
  void retirer(Fromage fromage);
}
