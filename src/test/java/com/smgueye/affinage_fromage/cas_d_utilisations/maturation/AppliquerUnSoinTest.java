package com.smgueye.affinage_fromage.cas_d_utilisations.maturation;

import com.smgueye.affinage_fromage.MaisonAffinage;
import com.smgueye.affinage_fromage.commun.CaveAffinageEntiteBuilder;
import com.smgueye.affinage_fromage.commun.FromageAgregatRacineBuilder;
import com.smgueye.affinage_fromage.commun.PlacerEnCaveCommandeBuilder;
import com.smgueye.affinage_fromage.domain.model.AffineurId;
import com.smgueye.affinage_fromage.domain.model.cave.CaveAffinage;
import com.smgueye.affinage_fromage.domain.model.fromage.Fromage;
import com.smgueye.affinage_fromage.domain.model.fromage.FromageId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Chapitre 3.1 : L'application des soins")
public class AppliquerUnSoinTest {

  private final MaisonAffinage laMaison = new MaisonAffinage();
  private final AffineurId affineurId = new AffineurId(UUID.randomUUID());

  private CaveAffinage uneCave;
  private Fromage unFromage;
  private FromageId fromageId;

  @BeforeEach
  void initialize() {
    this.uneCave = CaveAffinageEntiteBuilder.uneCave().construction();
    this.unFromage = FromageAgregatRacineBuilder.unBonFromage().construction();
    laMaison.confie(unFromage);
    laMaison.placeEnCave(
      PlacerEnCaveCommandeBuilder
        .uneCommandeDePlacement()
        .avecCave(uneCave)
        .avecFromage(unFromage)
        .construire());
  }

  @Test
  @DisplayName("Un fromage en maturation doit pouvoir recevoir des soins. (Happy path)")
  public void un_fromage_en_maturation_doit_pouvoir_recevoir_des_soins() {
/*    // Act
    LocalDate dateDuSoin = TimeTraveler.Moment().aujourdHui();
    laMaison.appliqueSoin(new AppliquerSoinCommande(
      fromageId,
      dateDuSoin,
      null,
      TypeDeSoinAffinage.RETOURNEMENT,
      affineurId
    ));

    // Assert
    Fromage leFromage = laMaison.fromageAvecId(fromageId).orElseThrow();
    // "Un fromage place en maison d'affinage doit pouvoir être retrouvé")
    assertThat(unFromage.aRecuSoin(TypeDeSoinAffinage.RETOURNEMENT, dateDuSoin)).isTrue();*/
  }

  @Test
  @DisplayName("Un fromage reçu ne doit pas pouvoir recevoir des soins.")
  public void un_fromage_recu_ne_doit_pas_pouvoir_recevoir_des_soins() {}

  @Test
  @DisplayName("Un fromage bloqué ne devrait pas pouvoir recevoir de soins.")
  public void un_fromage_bloque_ne_devrait_pas_pouvoir_recevoir_des_soins() {}

  @Test
  @DisplayName("Un fromage retiré ne devrait pas pouvoir recevoir de soins.")
  public void un_fromage_retire_ne_devrait_pas_pouvoir_recevoir_des_soins() {}

  @Test
  @DisplayName("Un fromage pret ne devrait pas pouvoir recevoir de soins.")
  public void un_fromage_pret_ne_devrait_pas_pouvoir_recevoir_des_soins() {}
}
