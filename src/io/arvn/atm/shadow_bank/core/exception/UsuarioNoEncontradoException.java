package io.arvn.atm.shadow_bank.core.exception;

public class UsuarioNoEncontradoException extends RuntimeException {

    public UsuarioNoEncontradoException(String mensaje, Throwable cause) {
        super(mensaje, cause);
    }

    public UsuarioNoEncontradoException(String message) {
        super(message);
    }
}
