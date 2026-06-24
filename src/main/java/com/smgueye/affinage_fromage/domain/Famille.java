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

  private final String name;

  private static final Map<String, Famille> BY_NAME = Stream
    .of(values())
    .collect(Collectors.toUnmodifiableMap(
      Famille::getName, Function.identity()));

  Famille(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public static Optional<Famille> fromName(String name) {
    return Optional.ofNullable(BY_NAME.get(name));
  }
}
