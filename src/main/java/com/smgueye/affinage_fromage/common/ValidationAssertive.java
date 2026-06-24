package com.smgueye.affinage_fromage.common;

import java.util.Objects;

public class ValidationAssertive {

  public void verifieArgumentNonNull(Object unObjet, String unMessage) {
    if (Objects.isNull(unObjet)) {
      throw new IllegalArgumentException(unMessage);
    }
  }

  public void verifieArgumentNonNullNiVide(String uneChaineDeCaracteres, String unMessage) {
    this.verifieArgumentNonNull(uneChaineDeCaracteres, unMessage);

    if (uneChaineDeCaracteres.trim().isEmpty()) {
      throw new IllegalArgumentException(unMessage);
    }
  }
}
