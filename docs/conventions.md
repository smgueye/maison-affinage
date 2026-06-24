# Conventions

## Les tests

### Structure

```java
/**
* Le metier devrait pouvoir lire les tests et comprendre ce qui se passe.
* 
* Commencer par le << Happy path >>
* Pour les assertions, penser à les regrouper pour donner une signification globale de ce qu'on vérifie.
* */
public class CasUtilisationATesterTest {

  @Test
  @DisplayName("Nom du scénario dans le cas d'utilisation")
  public void donner_le_nom_du_scenario() {
    // Arrange

    // Act

    // Assert
  }
}
```

### Règles concernant les fixtures

### Règles de nomenclature

- Ne pas suivre une nomenclature rigide.
- Nommer le test comme pour décrire le scenario à un autre collègue familier avec le domaine.
  (un business analyst ou un domain expert).
- Séparer les mots avec underscore afin d'améliorer la lisibilité (Surtout avec de longs noms).