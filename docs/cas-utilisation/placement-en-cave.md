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
participant "Maison d'affinage" as Maison << ApplicationService >> 
participant "Placement en Cave" as Service << DomaineService >> 
participant "Cave d'affinage" as Cave << AggregateRoot >> 
participant "Fromage" as Fromage << RootEntity >> 

Maison -> Service : placerUnFromqge(fromageId : FromageId)
Service -> Cave : placerUnFromage(fromageId : FromageId)
Cave -> Cave : peutAccueillirUnFromage(fromage: Fromage)
note right : inv #1 - la capacite \ninv #2 - la compatibilite 
Cave -> Cave : reserverUnePlace(fromageId : FromageId)
Cave --> Service : <<Fromage place>>

Service -> Fromage : marquerCommeEtantEnMaturation(periodeDeMaturation : PeriodeDeMaturation)
note right : inv #3 - La periode de maturation 
Fromage --> Fromage : marquerCommeEtantEnMaturation()
Fromage --> Fromage : <<Fromage en Maturation>> 
Fromage --> Service : <<Fromage en Maturation>> 
Cave --> Maison : <<Fromage place>>
@enduml
```


## Le modèle

### v1.09082026

```plantuml
class CaveAffinageId <<ValueObject>> {
-id: UUID
}

class FromageId <<ValueObject>> {
-id: UUID
}

class Fromage <<RootAggregate>> {
+marquerEnMaturation(periode : PeriodeDeMaturation) : void
-verifierLePlacementSurLaPeriode(periode: PeriodeDeMaturation) : void
}

class CaveAffinage <<RootAggregate>> {
+placerUnFromage(fromageId: FromageId) : void
-peutAccueillirUnFromage(fromageId: FromageId) : void
-verifierLaCapacite(fromageId: FromageId): void
-verifierLaCompatibiliteAvecFromage(plageAffinage: PlageAffinage)
-reserverUnePlace(fromageId: FromageId) : void
}

class PlacementEnCave <<DomaineService>> {
+placerUnFromage(\n  fromage: Fromage,\n  cave: CaveAffinage, \n  periode: PeriodeDeMaturation) : void;
}

Fromage --> FromageId
CaveAffinage -> CaveAffinageId
Fromage --> CaveAffinage
PlacementEnCave --> CaveAffinage
PlacementEnCave --> Fromage
```

### v1.0000000
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