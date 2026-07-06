package com.smgueye.affinage_fromage.commun.exceptions;

public class IncompatibiliteAvecLaPeriodeDurantLePlacement extends RuntimeException {

  public IncompatibiliteAvecLaPeriodeDurantLePlacement() {
    super("Un fromage ne peut être place que dans une seule cave sur une meme période.");
  }

  public IncompatibiliteAvecLaPeriodeDurantLePlacement(String message) {
    super(message);
  }
}
