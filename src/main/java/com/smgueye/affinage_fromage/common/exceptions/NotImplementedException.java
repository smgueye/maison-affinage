package com.smgueye.affinage_fromage.common.exceptions;

public class NotImplementedException extends UnsupportedOperationException {

    public NotImplementedException() {
      super("TODO - Fonctionnalité non implémentée pour le moment");
    }

    public NotImplementedException(String message) {
        super(message);
    }
}
