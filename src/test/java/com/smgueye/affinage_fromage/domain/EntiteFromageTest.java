package com.smgueye.affinage_fromage.domain;

import com.smgueye.affinage_fromage.commun.FromageAgregatRacineBuilder;
import com.smgueye.affinage_fromage.domain.etat.AucunEtat;
import com.smgueye.affinage_fromage.domain.fromage.Fromage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EntiteFromageTest {

  @Test
  @DisplayName("Construire un fromage sans identifiant est refuse")
  public void construire_un_fromage_sans_identifiant_est_refuse() {

    assertThatThrownBy(() -> {
      FromageAgregatRacineBuilder.unBonFromage()
        .avecId(null)
        .construction();
    }).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("Construire un fromage sans nom est refuse")
  public void construire_un_fromage_sans_nom_est_refuse() {

    assertThatThrownBy(() -> {
      FromageAgregatRacineBuilder
        .unBonFromage()
        .avecNom(null)
        .construction();
    }).isInstanceOf(IllegalArgumentException.class);

    assertThatThrownBy(() -> {
      FromageAgregatRacineBuilder
        .unBonFromage()
        .avecNom("")
        .construction();
    }).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("Construire un fromage sans poids est refuse")
  public void construire_un_fromage_sans_poids_est_refuse() {

    assertThatThrownBy(() -> {
      FromageAgregatRacineBuilder
        .unBonFromage()
        .avecPoids(null)
        .construction();
    }).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("Construire un fromage sans date de reception est refuse")
  public void construire_un_fromage_sans_date_de_reception_est_refuse() {

    assertThatThrownBy(() -> {
      FromageAgregatRacineBuilder
        .unFromageVide()
        .avecDateDeReception(null)
        .construction();
    }).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("Un fromage initialement construit ne doit être à aucun état avant réception")
  public void un_fromage_initialement_construit_ne_doit_etre_a_aucun_etat_avant_reception() {
      Fromage fromage = FromageAgregatRacineBuilder
        .unBonFromage()
        .construction();
      assertThat(fromage.etat()).isInstanceOf(AucunEtat.class);
  }

  @Test
  @DisplayName("Construire un fromage sans famille est refuse")
  public void construire_un_fromage_sans_famille_est_refuse() {

    assertThatThrownBy(() -> {
      FromageAgregatRacineBuilder
        .unBonFromage()
        .avecFamille(null)
        .construction();
    }).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("Construire un fromage sans identifiant artisan est refuse")
  public void construire_un_fromage_sans_identifiant_artisan_est_refuse() {

    assertThatThrownBy(() -> {
      FromageAgregatRacineBuilder
        .unBonFromage()
        .avecArtisanId(null)
        .construction();
    }).isInstanceOf(IllegalArgumentException.class);
  }
}
