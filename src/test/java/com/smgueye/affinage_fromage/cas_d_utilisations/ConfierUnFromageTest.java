package com.smgueye.affinage_fromage.cas_d_utilisations;

import com.smgueye.affinage_fromage.MaisonAffinage;
import com.smgueye.affinage_fromage.commun.FromageAgregatRacineBuilder;
import com.smgueye.affinage_fromage.domain.fromage.Fromage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfierUnFromageTest {

  @Test
  @DisplayName("Chapitre 1 : Confier un fromage à une maison d'affinage")
  public void confier_un_fromage_a_une_maison_d_affinage() {
    // Arrange
    MaisonAffinage maison = new MaisonAffinage();
    Fromage fromage = FromageAgregatRacineBuilder
      .unBonFromage()
      .construction();

    // Act
    maison.recoit(fromage);

    // Assert
    Optional<Fromage> leFromage = maison.fromageAvecId(fromage.id());
    assertThat(leFromage.isPresent())
      .describedAs("Un fromage place en maison d'affinage doit pouvoir être retrouvé")
      .isTrue();
    assertThat(leFromage.get().estRecu())
      .describedAs("Un fromage confié à la maison d'affinage doit être marqué comme reçu")
      .isTrue();
  }
}
