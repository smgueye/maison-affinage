package com.smgueye.affinage_fromage.domain;

import com.smgueye.affinage_fromage.MaisonAffinage;

import static org.assertj.core.api.Assertions.assertThat;

public class AgregatFromageTest {

  private final MaisonAffinage laMaisonAffinage = new MaisonAffinage();

 /* @Test
  @DisplayName("Un fromage deja en cave ne peut faire l'objet d'un replacement un seconde fois")
  public void un_fromage_deja_en_cave_ne_peut_faire_l_objet_d_un_replacement_un_seconde_fois() throws CapaciteMaximaleDepasseeException, IncompatibiliteAvecCaveException {
    // Arrange
    Fromage unFromage = FromageAgregatRacineBuilder
      .unBonFromage()
      .construction();
    CaveAffinage uneCave = CaveAffinageEntiteBuilder
      .uneCave()
      .construction();

    laMaisonAffinage.recoit(unFromage);
    laMaisonAffinage.placeEnCave(PlacerEnCaveCommandeBuilder
      .uneCommandeDePlacement()
      .avecCave(uneCave)
      .avecFromage(unFromage)
      .construire());

    // Act & Assert
    assertThatExceptionOfType(IllegalArgumentException.class)
      .isThrownBy(() -> {
        CaveAffinage une2eCave = CaveAffinageEntiteBuilder.uneCave().construction();
        laMaisonAffinage.placeEnCave(PlacerEnCaveCommandeBuilder
          .uneCommandeDePlacement()
          .avecCave(une2eCave)
          .avecFromage(unFromage)
          .construire());
      })
      .withMessage(Message.FROMAGE_DEJA_EN_CAVE);
  }
*/
  /*@Test
  @DisplayName("Une cave qui possede deja un fromage ne peut pas le replacer")
  public void une_cave_qui_possede_deja_un_fromage_ne_peut_pas_le_replacer() throws CapaciteMaximaleDepasseeException, IncompatibiliteAvecCaveException {
    Fromage unFromage = FromageAgregatRacineBuilder.unBonFromage().construction();
    CaveAffinage uneCave = CaveAffinageEntiteBuilder.uneValide().construction();
    laMaisonAffinage.recoit(unFromage);
    laMaisonAffinage.placeEnCave(PlacerEnCaveCommandeBuilder.uneCommandeDePlacement().avecCave(uneCave).avecFromage(unFromage).construire());

    // Act & Assert
    assertThatExceptionOfType(IllegalArgumentException.class)
      .isThrownBy(() -> {
        uneCave.place(unFromage);
      })
      .withMessage(Message.CAVE_ACCUEIL_DEJA_FROMAGE);
  }*/

}
