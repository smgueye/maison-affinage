package com.smgueye.affinage_fromage.commun.exceptions;

public class NotImplementedException extends UnsupportedOperationException {

    public NotImplementedException() {
      super("TODO - Fonctionnalité non implémentée pour le moment");
    }

    public NotImplementedException(String message) {
        super(message);
    }
}
