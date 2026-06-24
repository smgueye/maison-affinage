package com.smgueye.affinage_fromage;

import com.smgueye.affinage_fromage.domain.*;
import com.smgueye.affinage_fromage.domain.etat.EtatRecu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfierUnFromageAUneMaisonAffinageTest {

  @Test
  @DisplayName("Confier un fromage à une maison d'affinage")
  public void test_confier_un_fromage() {
    // Arrange
    MaisonAffinageFacade maison = new MaisonAffinageFacade();
    FromageId fromageId = new FromageId(UUID.randomUUID());
    ArtisantId artisantId = new ArtisantId(UUID.randomUUID());

    // Act
    maison.recoit(unFromageBleu(fromageId, artisantId));

    // Assert
    Optional<Fromage> leFromage = maison.fromageAvecId(fromageId);
    assertThat(leFromage.isPresent()).isTrue();
    assertThat(leFromage.get().estRecu()).isTrue();
  }

  // TODO - A placer dans une fixture
  private static Fromage unFromageBleu(FromageId fromageId, ArtisantId artisantId) {
    return new Fromage(fromageId, "Bleu xxx - A", new Poids(200), LocalDate.now(), Famille.BLEU, artisantId);
  }
}
