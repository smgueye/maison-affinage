package com.smgueye.affinage_fromage.domain;

import lombok.Getter;

import java.util.UUID;

@Getter
public class CaveAffinageId {
  private final UUID id;

  public CaveAffinageId(UUID id) {
    this.id = id;
  }
}
