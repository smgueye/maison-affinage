package com.smgueye.affinage_fromage.domain;

import com.smgueye.affinage_fromage.common.ValueObject;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ArtisantId extends ValueObject {
  private UUID id;

  public ArtisantId(UUID id) {
    this.id = id;
  }
}
