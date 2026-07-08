
@startuml

class Fromage<<Agregat>> {
-actions  : List<Action>
-incidents: List<Incident>
+appliquerSoin(soin: Soin): void
+declarerIncident(incident: Incident): void
+marquerBloquer(marque: Marquage): void
+marquerRetire(marque: Marquage): void
+marquerPret(marque: Marquage): void
-ajouterAction(actio: Action): void
-ajouterIncident(incident: Incident): void
-changerEtat(nouvelEtat: Etat)
}

class Etat {
+appliquerSoin(Fromange fromage, soin: Soin): void
+declarerIncident(Fromange fromage, incident: Incident)
+marquerBloquer(Fromange fromage, marque: Marquage): void
+marquerRetire(Fromange fromage, marque: Marquage): void
+marquerPret(Fromange fromage, marque: Marquage): void
}

abstract class Action {
-fromageId : FromageId
-date : LocalDate
-observations: String
-affineurId: AffineurId
}

class Soin {
-type: TypeDeSoin
-realisePar: AffineurId
}

class Marquage {
-poidsFinal : double
-motif : String
}

class Incident {
-date : LocalDate
-type : TypeIncident
-criticite : NiveauCriticite
-commentaire : String
}

exception ExeptionMetier

Action <|-- Soin
Action <|-- Marquage


Fromage --> Action
Fromage --> Etat
Fromage --> Incident
Etat --> ExeptionMetier
@enduml
