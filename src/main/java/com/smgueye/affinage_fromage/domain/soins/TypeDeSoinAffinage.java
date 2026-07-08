package com.smgueye.affinage_fromage.domain.soins;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum TypeDeSoinAffinage {
  RETOURNEMENT("Retournement"),
  BROSSAGE("Brossage"),
  LAVAGE_CROUTE("Lavage de croûte"),
  PIQUAGE("Piquage"),
  CONTROLE_OLFACTIF("contrôle olfactif");

  private final String soin;

  private static final Map<String, TypeDeSoinAffinage> PAR_NOM_DU_SOIN = Stream
    .of(values())
    .collect(Collectors.toUnmodifiableMap(
      TypeDeSoinAffinage::soin, Function.identity()));

  TypeDeSoinAffinage(String nomDuSoin) {
    soin = nomDuSoin;
  }

  public String soin() {
    return soin;
  }

  public static Optional<TypeDeSoinAffinage> aPartirDe(String nomDuSoin) {
    return Optional.ofNullable(PAR_NOM_DU_SOIN.get(nomDuSoin));
  }
}
