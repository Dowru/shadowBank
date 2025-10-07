package io.arvn.atm.shadow_bank.core.exception;

public class CajeroException extends RuntimeException {

    public CajeroException(String mensaje, Throwable cause) {
        super(mensaje, cause);
    }
    public CajeroException(String message) {
        super(message);
    }
}
