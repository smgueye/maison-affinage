package com.smgueye.affinage_fromage.domain;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum SoinAffinage {
  RETOURNEMENT("Retournement"),
  BROSSAGE("Brossage"),
  LAVAGE_CROUTE("Lavage de croûte"),
  PIQUAGE("Piquage"),
  CONTROLE_OLFACTIF("contrôle olfactif");

  private final String soin;

  private static final Map<String, SoinAffinage> PAR_NOM_DU_SOIN = Stream
    .of(values())
    .collect(Collectors.toUnmodifiableMap(
      SoinAffinage::getSoin, Function.identity()));

  SoinAffinage(String nomDuSoin) {
    soin = nomDuSoin;
  }

  public String getSoin() {
    return soin;
  }

  public static Optional<SoinAffinage> fromSoin(String nomDuSoin) {
    return Optional.ofNullable(PAR_NOM_DU_SOIN.get(nomDuSoin));
  }
}
