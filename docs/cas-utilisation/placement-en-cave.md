# Placement en cave

## Cas d'utilisation

Acteur : Maison d'affinement

Objectif : Pouvoir placer un fromage en cave.

Succès : Le fromage est placé dans la cave.

Échec :
- Une cave ne peut pas dépasser sa capacité maximale.
- Un fromage ne peut être place dans une cave incompatible.
  La température cible et l’humidité cible de la cave doivent entrer dans la plage acceptable de la famille du fromage.
- Un fromage ne peut être place que dans une seule cave sur une meme période.

## Activité

```plantuml
@startuml
start
:Confier et recevoir un fromage;
:Placer le fromage en cave;
: ...;
 stop
@enduml
```

## Etat transition

```plantuml
@startuml
[*] --> EtatRecu
EtatRecu -> EtatEnMaturation : placementEnCave()
EtatEnMaturation --> [*]
 @enduml 
```

## Sequence

```plantuml
@startuml
Client -> Fromage : placerEnCave(CaveAffinage cave)

activate Fromage
    Fromage -> CaveAffinage : placerEnCave(Fromage f, PeriodeDeMaturation p)
    activate CaveAffinage
    
        CaveAffinage --> CaveAffinage : estCompatible(Fromage fromage)
        activate CaveAffinage
            alt cas echeant
                CaveAffinage -> CaveAffinage : placerEnCave(Fromage fromage)
                CaveAffinage --> Fromage : Fromage en maturation
            else
                CaveAffinage --> Fromage : Exception metier
                note right : Lever une exc metier
            end
        deactivate CaveAffinage

    deactivate CaveAffinage
    Fromage --> Client

deactivate Fromage
@enduml
```


## Le modèle

```plantuml
class CaveAffinageId <<ValueObject>> {
    -id: UUID
}

class FromageId <<ValueObject>> {
    -id: UUID
}

class Fromage <<RootAggregate>> {
    -caveAffinageId: CaveAffinageId
    -void verifierEtat()
    +void placerEnCave(uneCommande: PlacerEnCaveCommande)
}

class CaveAffinage <<Entity>> {
    -fromageId: Set<FromageId>
    -void peutAccueillir(unFromage: Fromage)
    +void accueillir((unFromage: Fromage)
}

Fromage --> FromageId
CaveAffinage --> CaveAffinageId
Fromage --> CaveAffinage
```