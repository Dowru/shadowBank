package io.arvn.atm.shadow_bank.core.util;

import io.arvn.atm.shadow_bank.model.ClienteDTO;

public class ContextUtil {

    private static ThreadLocal<ClienteDTO> clienteContexto = new ThreadLocal<>();

    private ContextUtil() {}

    public static void addUserToContext(ClienteDTO cliente) {
        clienteContexto.set(cliente);

    }

    public static ClienteDTO getClienteContexto() {
        return clienteContexto.get();
    }

    public static void finalizeContext(){
        clienteContexto.remove();
    }
}
