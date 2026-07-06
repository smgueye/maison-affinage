package com.smgueye.affinage_fromage.commun.exceptions;

import com.smgueye.affinage_fromage.commun.messages.Message;

public class IncompatibiliteAvecCaveException extends RuntimeException {

  public IncompatibiliteAvecCaveException() {
    super(Message.CAVE_INCOMPATIBLE_EXCEPTION);
  }

  public IncompatibiliteAvecCaveException(String message) {
    super(String.format("%s : %s", Message.CAVE_INCOMPATIBLE_EXCEPTION, message));
  }
}
