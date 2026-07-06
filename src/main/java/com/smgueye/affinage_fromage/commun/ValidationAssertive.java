package com.smgueye.affinage_fromage.commun;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class ValidationAssertive {

  public void verifieArgumentEstNull(Object unObjet, String unMessage) {
    if (Objects.nonNull(unObjet)) {
      throw new IllegalArgumentException(unMessage);
    }
  }

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

  public void verifieArgumentEstFaux(Object unObjet, String unMessage) {
    if (Boolean.FALSE.equals(unObjet)) {
      throw new IllegalArgumentException(unMessage);
    }
  }

  protected void verifieArgumentIntervalDeDateValide(LocalDate debut, LocalDate fin, String unMessage) {
    if (debut.isAfter(fin)) {
      throw new IllegalArgumentException(unMessage);
    }
  }

  protected void verifieArgumentListEstNonNullNiVide(List uneListe, String unMessage) {
    if (Objects.isNull(uneListe) || uneListe.isEmpty()) {
      throw new IllegalArgumentException(unMessage);
    }
  }
}
