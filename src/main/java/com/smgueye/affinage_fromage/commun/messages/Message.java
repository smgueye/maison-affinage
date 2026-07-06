package com.smgueye.affinage_fromage.commun.messages;

public final class Message {

  private Message() {}

  public static final String FROMAGE_DEJA_EN_CAVE = "Le fromage semble deja être place en cave.";
  public static final String CAVE_ACCUEIL_DEJA_FROMAGE = "La cave accueil deja ce fromage.";
  public static final String CAPACITE_MAXIMAL_DEPASSEE_EXCEPTION = "Une cave ne peut pas dépasser sa capacité maximale.";
  public static final String CAVE_INCOMPATIBLE_EXCEPTION ="Un fromage ne peut être place dans une cave incompatible.\n" +
    "La température cible et l’humidité cible de la cave doivent entrer dans la plage acceptable " +
    "de la famille du fromage.";
  public static final String HUMIDITE_INCOMPATIBLE_AVEC_LA_CAVE = "Vérifier la compatibilité avec l'humidité";
  public static final String TEMPERATURE_INCOMPATIBLE_AVEC_LA_CAVE = "Vérifier la compatibilité avec la température";
}
