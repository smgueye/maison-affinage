package com.smgueye.affinage_fromage.cas_d_utilisations;

import com.smgueye.affinage_fromage.MaisonAffinage;
import com.smgueye.affinage_fromage.commandes.PlacerEnCaveCommande;
import com.smgueye.affinage_fromage.commun.CaveAffinageEntiteBuilder;
import com.smgueye.affinage_fromage.commun.FromageAgregatRacineBuilder;
import com.smgueye.affinage_fromage.commun.PlacerEnCaveCommandeBuilder;
import com.smgueye.affinage_fromage.commun.exceptions.CapaciteMaximaleDepasseeException;
import com.smgueye.affinage_fromage.commun.exceptions.ExceptionMetier;
import com.smgueye.affinage_fromage.commun.exceptions.IncompatibiliteAvecCaveException;
import com.smgueye.affinage_fromage.commun.exceptions.IncompatibiliteAvecLaPeriodeDurantLePlacement;
import com.smgueye.affinage_fromage.commun.messages.Message;
import com.smgueye.affinage_fromage.domain.IntervalHumidite;
import com.smgueye.affinage_fromage.domain.IntervalTemperature;
import com.smgueye.affinage_fromage.domain.PeriodeDeMaturation;
import com.smgueye.affinage_fromage.domain.PlageAffinage;
import com.smgueye.affinage_fromage.domain.cave.CaveAffinage;
import com.smgueye.affinage_fromage.domain.fromage.Fromage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Period;
import java.util.stream.Stream;

import static com.smgueye.affinage_fromage.commun.TimeTraveler.Moment;
import static com.smgueye.affinage_fromage.commun.messages.Message.HUMIDITE_INCOMPATIBLE_AVEC_LA_CAVE;
import static com.smgueye.affinage_fromage.commun.messages.Message.TEMPERATURE_INCOMPATIBLE_AVEC_LA_CAVE;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@DisplayName("Chapitre 2 : Le placement en cave")
public class LePlacementEnCaveTest {

  private final MaisonAffinage laMaisonAffinage = new MaisonAffinage();

  @Test
  @DisplayName("Le fromage est placé dans la cave (Happy Path)")
  public void le_fromage_est_place_dans_la_cave() throws com.smgueye.affinage_fromage.commun.exceptions.ExceptionMetier, CapaciteMaximaleDepasseeException, IncompatibiliteAvecCaveException {
    // Arrange
    Fromage unFromage = FromageAgregatRacineBuilder
      .unBonFromage()
      .construction();
    CaveAffinage uneCave = CaveAffinageEntiteBuilder
      .uneCave()
      .construction();
    // TODO - Peut être qu'implémenter un état recu dans le builder directement serait mieux ?
    //        Ex. Builder.unBonFromage().recuA(maisonAffinage)...
    laMaisonAffinage.recoit(unFromage);

    // Act
    laMaisonAffinage.placeEnCave(PlacerEnCaveCommandeBuilder.uneCommandeDePlacement()
      .avecCave(uneCave)
      .avecFromage(unFromage)
      .construire());

    // Assert
    Fromage leFromage = laMaisonAffinage
      .fromageAvecId(unFromage.id())
      .orElseThrow(() -> new ExceptionMetier("Un fromage placé en maison d'affinage doit pouvoir être retrouvé"));

    assertThat(leFromage.estEnMaturation())
      .describedAs("Un fromage placé en cave doit être en cours de maturation")
      .isTrue();
    assertThat(uneCave.accueilleDeja(leFromage.id()));
    assertThat(unFromage.periodeDeMaturation()).isEqualTo(new PeriodeDeMaturation(Moment().aujourdHui(), Moment().dansUneSemaine()));
    assertThat(leFromage.estPlaceDansLaCave(uneCave))
      .describedAs("Un fromage placé dans un cave doit pouvoir y être retrouvé")
      .isTrue();
  }

  @Test
  @DisplayName("Invariant 1 : Une cave ne peut pas accueillir un nombre de fromages dépassant sa capacité maximale")
  public void une_cave_ne_peut_pas_accueillir_un_nombre_de_fromages_depassant_sa_capacite_maximale() throws CapaciteMaximaleDepasseeException, IncompatibiliteAvecCaveException {
    // Arrange
    CaveAffinage uneCave = CaveAffinageEntiteBuilder.uneCave().construction();
    for (int i = 1; i <= uneCave.capaciteMaximale(); i++) {
      PlacerEnCaveCommande uneCommande = PlacerEnCaveCommandeBuilder
        .uneCommandeDePlacement()
        .avecCave(uneCave)
        .construire();
      laMaisonAffinage.recoit(uneCommande.fromage());
      laMaisonAffinage.placeEnCave(uneCommande);
    }

    // Act & Assert
    assertThatExceptionOfType(CapaciteMaximaleDepasseeException.class)
      .isThrownBy(() -> {
        laMaisonAffinage.placeEnCave(PlacerEnCaveCommandeBuilder
          .uneCommandeDePlacement()
          .avecCave(uneCave)
          .construire());
      })
      .withMessage(Message.CAPACITE_MAXIMAL_DEPASSEE_EXCEPTION);
  }

  @DisplayName("Invariant 2 : Un fromage ne peut être placé dans une cave incompatible")
  @Nested
  class UnFromageNePeutEtrePlaceDansUneCaveIncompatible {
    // NOTE - Par compatible, on veut que température cible et l’humidité cible de la cave soient
    //        dans la plage acceptable de la famille du fromage.

    private final CaveAffinage uneCaveAvecDifferentesPlagesAffinement = CaveAffinageEntiteBuilder.uneCave()
      .avecTemperatureCible(10)
      .avecHumiditeCible(100)
      .construction();

    @Test
    @DisplayName("Un fromage ne peut être place dans une cave avec une température incompatible")
    public void un_fromage_ne_peut_etre_place_dans_une_cave_avec_une_incompatibilite_de_temperature() {
      Fromage unFromage = FromageAgregatRacineBuilder
        .unBonFromage()
        .avecPlanAffinage(new PlageAffinage(new IntervalTemperature(12, 15), new IntervalHumidite(90, 105)))
        .construction();
      laMaisonAffinage.recoit(unFromage);

      assertThatExceptionOfType(IncompatibiliteAvecCaveException.class)
        .describedAs("Un fromage avec une temperature invalide ne doit pas pouvoir être place")
        .isThrownBy(() -> laMaisonAffinage.placeEnCave(PlacerEnCaveCommandeBuilder
          .uneCommandeDePlacement()
          .avecCave(uneCaveAvecDifferentesPlagesAffinement)
          .avecFromage(unFromage)
          .construire()))
        .withMessageContaining(TEMPERATURE_INCOMPATIBLE_AVEC_LA_CAVE);
    }

    @Test
    @DisplayName("Un fromage ne peut être place dans une cave avec une humidite incompatible")
    public void un_fromage_ne_peut_etre_place_dans_une_cave_avec_une_incompatibilite_d_humidite() {
      Fromage unFromage = FromageAgregatRacineBuilder
        .unBonFromage()
        .avecPlanAffinage(new PlageAffinage(new IntervalTemperature(10, 13), new IntervalHumidite(90, 95)))
        .construction();
      laMaisonAffinage.recoit(unFromage);

      assertThatExceptionOfType(IncompatibiliteAvecCaveException.class)
        .describedAs("Un fromage avec une humidite invalide ne doit pas pouvoir être place")
        .isThrownBy(() -> laMaisonAffinage.placeEnCave(PlacerEnCaveCommandeBuilder
          .uneCommandeDePlacement()
          .avecCave(uneCaveAvecDifferentesPlagesAffinement)
          .avecFromage(unFromage)
          .construire()))
        .withMessageContaining(HUMIDITE_INCOMPATIBLE_AVEC_LA_CAVE);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("fromageAvecTemperatureEtHumiditeIncompatibles")
    @DisplayName("Un fromage compatible avec une cave peut y être placé")
    public void un_fromage_compatible_avec_une_cave_peut_y_etre_place(String scenario, Fromage unFromage) {
      CaveAffinage uneCave = CaveAffinageEntiteBuilder.uneCave().avecTemperatureCible(10).avecHumiditeCible(90).construction();
      assertThatCode(() -> laMaisonAffinage.placeEnCave(
          PlacerEnCaveCommandeBuilder
            .uneCommandeDePlacement()
            .avecCave(uneCave)
            .avecFromage(unFromage)
            .construire()))
        .describedAs(scenario)
        .doesNotThrowAnyException();
    }

    static Stream<Arguments> fromageAvecTemperatureEtHumiditeIncompatibles() {
      String scenario = "Un fromage avec une temperature et une humidite valident doit pouvoir " +
        "être placé";
      return Stream.of(
        arguments(
          scenario,
          FromageAgregatRacineBuilder
            .unBonFromage()
            .avecPlanAffinage(new PlageAffinage(new IntervalTemperature(9, 11), new IntervalHumidite(90, 105)))
            .construction()),
        arguments(
          scenario,
          FromageAgregatRacineBuilder
            .unBonFromage()
            .avecPlanAffinage(new PlageAffinage(new IntervalTemperature(10, 15), new IntervalHumidite(90, 105)))
            .construction()),
        arguments(
          scenario,
          FromageAgregatRacineBuilder
            .unBonFromage()
            .avecPlanAffinage(new PlageAffinage(new IntervalTemperature(5, 10), new IntervalHumidite(90, 105)))
            .construction()),
        arguments(
          scenario,
          FromageAgregatRacineBuilder
            .unBonFromage()
            .avecPlanAffinage(new PlageAffinage(new IntervalTemperature(5, 15), new IntervalHumidite(0, 90)))
            .construction()),
        arguments(
          scenario,
          FromageAgregatRacineBuilder
            .unBonFromage()
            .avecPlanAffinage(new PlageAffinage(new IntervalTemperature(5, 15), new IntervalHumidite(90, 100)))
            .construction())
      );
    }
  }

  @DisplayName("Invariant 3 : Un fromage ne peut être place que dans une seule cave sur une meme période")
  @Nested
  class UnFromageNePeutEtrePlaceQueDansUneCaveSurUneMemePeriode {

    @ParameterizedTest(name = "{0}")
    @MethodSource("fromageAvecPeriodeDeCompatibiliteValide")
    public void un_fromage_peut_etre_place_dans_n_importe_quelle_cave_sur_une_periode_differente(String leScenario,
                                                                                                 Fromage unFromage,
                                                                                                 PeriodeDeMaturation periode) {
      // Arrange
      laMaisonAffinage.recoit(unFromage);
      CaveAffinage uneCave = CaveAffinageEntiteBuilder.uneCave().construction();
      laMaisonAffinage.placeEnCave(PlacerEnCaveCommandeBuilder
        .uneCommandeDePlacement()
        .avecFromage(unFromage)
        .avecCave(uneCave)
        .construire());

      // Act & Assert
      assertThat(unFromage.periodeDeMaturation())
        .describedAs("Un fromage placé une 1er fois doit avoir une période de maturation")
        .isNotNull();
      assertThatCode(() -> {
          laMaisonAffinage.placeEnCave(PlacerEnCaveCommandeBuilder
            .uneCommandeDePlacement()
            .avecCave(uneCave)
            .avecFromage(unFromage)
            .avecPeriodeDeMaturation(periode)
            .construire());
      })
        .describedAs(leScenario)
        .doesNotThrowAnyException();
      assertThat(unFromage.periodeDeMaturation().equals(periode))
        .describedAs("La nouvelle période de maturation du fromage doit etre celle passée dans la commande")
        .isTrue();
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("fromageAvecPeriodeDeMaturationIncompatibles")
    public void un_fromage_ne_peut_etre_place_que_dans_une_meme_sur_la_meme_periode(String leScenario,
                                                                                    Fromage unFromage,
                                                                                    PeriodeDeMaturation periode) {
      // Arrange
      laMaisonAffinage.recoit(unFromage);
      CaveAffinage uneCave = CaveAffinageEntiteBuilder.uneCave().construction();
      laMaisonAffinage.placeEnCave(PlacerEnCaveCommandeBuilder
        .uneCommandeDePlacement()
        .avecFromage(unFromage)
        .avecCave(uneCave)
        .construire());

      // Act & Assert
      assertThat(unFromage.periodeDeMaturation()).isNotNull();
      assertThatExceptionOfType(IncompatibiliteAvecLaPeriodeDurantLePlacement.class)
        .describedAs(leScenario)
        .isThrownBy(() -> {
          laMaisonAffinage.placeEnCave(PlacerEnCaveCommandeBuilder
            .uneCommandeDePlacement()
            .avecCave(uneCave)
            .avecFromage(unFromage)
            .avecPeriodeDeMaturation(periode)
            .construire());
        });
    }


    static Stream<Arguments> fromageAvecPeriodeDeCompatibiliteValide() {
      final Fromage unFromage = FromageAgregatRacineBuilder.unBonFromage().construction();
      final String scenario = "Un fromage avec une période de maturité différente doit pouvoir etre placé. Cas %s";

      return Stream.of(
        arguments(String.format(scenario, "STRICTEMENT AVANT"), unFromage,
          new PeriodeDeMaturation(Moment().ilYa(Period.ofDays(5)), Moment().hier())),
        arguments(String.format(scenario, "STRICTEMENT AVANT"), unFromage,
          new PeriodeDeMaturation(Moment().hier(), Moment().hier())),
        arguments(String.format(scenario, "STRICTEMENT APRES"), unFromage,
          new PeriodeDeMaturation(Moment().dansUneSemaineEt(Period.ofDays(1)), Moment().dansUneSemaineEt(Period.ofDays(1)))),
        arguments(String.format(scenario, "STRICTEMENT APRES"), unFromage,
          new PeriodeDeMaturation(Moment().dansUneSemaineEt(Period.ofDays(1)), Moment().dansUneSemaineEt(Period.ofDays(3)))));
    }

    static Stream<Arguments> fromageAvecPeriodeDeMaturationIncompatibles() {
      final Fromage unFromage = FromageAgregatRacineBuilder.unBonFromage().construction();
      final String scenario = "NB : **La cave Possède deja le fromage. Sa période est : " +
                              "DEBUT: AUJOURD'HUI - FIN: DANS UNE SEMAINE. " +
                              "Un fromage entre: \n %s doit générer une exception métier.";
      return Stream.of(
        arguments(String.format(scenario, "La MEME période ou une période INCLUSE"), unFromage,
          new PeriodeDeMaturation(Moment().hier(), Moment().xAvantUneSemaine(Period.ofDays(1)))),
        arguments(String.format(scenario, "HIER -- DEMAIN"), unFromage,
          new PeriodeDeMaturation(Moment().hier(), Moment().demain())),
        arguments(String.format(scenario, "1J AVANT UNE SEMAINE -- 1J APRES UNE SEMAINE"), unFromage,
          new PeriodeDeMaturation(Moment().xAvantUneSemaine(Period.ofDays(1)), Moment().dansUneSemaineEt(Period.ofDays(1))),
        arguments(String.format(scenario, "HIER -- AUJOURD'HUI"), unFromage,
          new PeriodeDeMaturation(Moment().hier(), Moment().aujourdHui())),
        arguments(String.format(scenario, "DANS 1 SEMAINE -- 1 SEMAINE + 1 JOUR"),
          unFromage, new PeriodeDeMaturation(Moment().dansUneSemaine(), Moment().dansUneSemaineEt(Period.ofDays(1))))),
        arguments(String.format(scenario, "AUJOURD'HUI -- DEMAIN"), unFromage,
          new PeriodeDeMaturation(Moment().aujourdHui(), Moment().demain())),
        arguments(String.format(scenario, "1 JOUR AVANT 1 SEMAINE -- DANS 1 SEMAINE"), unFromage,
          new PeriodeDeMaturation(Moment().xAvantUneSemaine(Period.ofDays(1)), Moment().dansUneSemaine()))
      );
    }
  }
}
