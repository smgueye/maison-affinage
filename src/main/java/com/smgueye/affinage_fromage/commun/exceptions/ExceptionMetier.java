package com.smgueye.affinage_fromage.commun.exceptions;

public class ExceptionMetier extends RuntimeException {

  public ExceptionMetier() {
    super("Exception générale metier");
  }

  public ExceptionMetier(String unMessage) {
    super(unMessage);
  }
}
