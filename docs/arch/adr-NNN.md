# Fromagerie

## ADR-1 : Changer l'agrégat racine de Fromage à CaveAffinage
Commit : ...

## status
Accepted

## context
Le fromage en soi est un concept utile en dehors de l'agrégat tout comme la cave.
Il est bien possible de vouloir accès à un fromage sans la cave et vice versa.
Cependant, ce qui est fait : Fromage et la cave forme un agrégat. Fromage est choisie comme entité racine de l'agrégat.

Le control de l'invariant : Une cave ne devrait pas dépasser sa capacité maximale.
- Il se fait dans le fromage alors que ce concept appartient naturellement à la cave.
- Ce controle de l'invariant se fait sur plusieurs fromages, donc un seul ne peut garantir la satisfaction de ce dernier.
- ** Le placement de la cave implique la modification de plusieurs agrégats du meme niveau en meme temps (Violation DDD).

## decision
Trois (') decisions vont être prisent : 

1. Faire de la cave un agrégat du placement du fromage.
2. Faire qu'aucun agrégat n'englobant la frontière du domaine modèle.
3. Replacer les invariants dans leur agrégat respectif.
4. Utiliser un Domain Service pour le placement en cave

## Consequences

### Positives
- Deux agregats ne sont plus modifiés dans une meme transaction.

### Negatives
...
