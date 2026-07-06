package com.smgueye.affinage_fromage.commun;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAmount;
import java.util.Objects;

public class TimeTraveler {

  private final Clock clock;

  private TimeTraveler(Clock clock) {
    this.clock = Objects.requireNonNull(clock);
  }
  
  public LocalDate ilYa(TemporalAmount tempsASoustraire) {
    return aujourdHui().minus(tempsASoustraire);
  }

  public LocalDate hier() {
    return aujourdHui().minusDays(1);
  }

  public LocalDate aujourdHui() {
    return LocalDate.now(clock);
  }

  public LocalDate demain() {
    return aujourdHui().plusDays(1);
  }

  public LocalDate xAvantUneSemaine(TemporalAmount tempsASoustraire) {
    return demain().minus(tempsASoustraire);
  }

  public LocalDate dansUneSemaine() {
    return aujourdHui().plusWeeks(1);
  }

  public LocalDate dansUneSemaineEt(TemporalAmount tempsAAjouter) {
    return aujourdHui().plusWeeks(1).plus(tempsAAjouter);
  }

  public static TimeTraveler Moment() {
    return new TimeTraveler(Clock.fixed(
      Instant.parse("2026-07-01T00:00:00Z"),
      ZoneId.of("Europe/Paris")
    ));
  }
}
