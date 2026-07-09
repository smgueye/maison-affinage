## Sequence

```plantuml
participant "Maison d'affinage" as Maison << ApplicationService >>
participant "Fromage" as RootAggregateFromage << AggregateRoot >>
participant "Fromage" as EntityFromage << Entity >>

Maison -> RootAggregateFromage : confieAMaisonAffinage()
RootAggregateFromage -> EntityFromage : marqueCommeRecu()
EntityFromage --> RootAggregateFromage : <<Fromage Recu>>
RootAggregateFromage --> Maison : <<Fromage Confie>>
```