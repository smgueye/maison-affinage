package com.smgueye.affinage_fromage.domain.incident;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum TypeIncident {
  NORMAL("Normal"),
  CRITIQUE("Critique");

  private final String type;

  public static final Map<String, TypeIncident> BY_TYPE = Arrays
    .stream(TypeIncident.values())
    .collect(Collectors.toUnmodifiableMap(
      TypeIncident::getType, Function.identity()));

  TypeIncident(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

  public static Optional<TypeIncident> fromType(String typeName) {
    return Optional.ofNullable(BY_TYPE.get(typeName));
  }
}
