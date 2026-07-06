package com.smgueye.affinage_fromage.commun.exceptions;

import com.smgueye.affinage_fromage.commun.messages.Message;

public class CapaciteMaximaleDepasseeException extends RuntimeException {

  public CapaciteMaximaleDepasseeException() {
    super(Message.CAPACITE_MAXIMAL_DEPASSEE_EXCEPTION);
  }

  public CapaciteMaximaleDepasseeException(String unMessage) {
    super(unMessage);
  }
}
