package io.arvn.atm.shadow_bank.core.exception;

public class UsuarioExistenteException extends RuntimeException {

    public UsuarioExistenteException(String mensaje, Throwable cause) {
        super(mensaje, cause);
    }

    public UsuarioExistenteException(String message) {
        super(message);
    }
}
