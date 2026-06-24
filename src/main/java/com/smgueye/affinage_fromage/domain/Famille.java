package com.smgueye.affinage_fromage.domain;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Famille {
  PATE_MOLLE("Pâte molle"),
  PATE_PRESSEE("Pâte pressée"),
  BLEU("Bleu"),
  CROUTE_LAVEE("Croûte lavée"),
  CHEVRE("chèvre"),
  BREBIS("Brebis");

  private final String nom;

  private static final Map<String, Famille> PAR_NOM = Stream
    .of(values())
    .collect(Collectors.toUnmodifiableMap(
      Famille::nom, Function.identity()));

  Famille(String nom) {
    this.nom = nom;
  }

  public String nom() {
    return nom;
  }

  public static Optional<Famille> chercherParLeNom(String unNom) {
    return Optional.ofNullable(PAR_NOM.get(unNom));
  }
}
