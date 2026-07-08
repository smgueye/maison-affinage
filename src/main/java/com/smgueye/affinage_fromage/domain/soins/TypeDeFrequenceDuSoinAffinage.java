package com.smgueye.affinage_fromage.domain.soins;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum TypeDeFrequenceDuSoinAffinage {
  QUOTIDIEN("QUOTIDIEN"),
  TOUS_LES_DEUX_JOURS("Tous les deux jours"),
  A_L_ENTREE_DE_LA_CAVE("A l'entrée de la cave"),
  AVANT_DE_SORTIR_DE_LA_CAVE("Avant de sortir de la cave"),
  HEBDOMADAIRE("Hebdomaire"),
  SUR_DEMANDE("Sur demande"),;

  private static final Map<String, TypeDeFrequenceDuSoinAffinage> PAR_FREQUENCE_DE_SOIN = Stream
    .of(TypeDeFrequenceDuSoinAffinage.values())
    .collect(Collectors.toUnmodifiableMap(
      TypeDeFrequenceDuSoinAffinage::frequenceDuSoin, Function.identity()));

  private final String frequenceDuSoin;

  TypeDeFrequenceDuSoinAffinage(String frequenceDuSoin) {
    this.frequenceDuSoin = frequenceDuSoin;
  }

  public String frequenceDuSoin() {
    return frequenceDuSoin;
  }

  public static Optional<TypeDeFrequenceDuSoinAffinage> aPartirDe(String nomDeLaFrequenceDuSoin) {
    return Optional.ofNullable(PAR_FREQUENCE_DE_SOIN.get(nomDeLaFrequenceDuSoin));
  }
}
