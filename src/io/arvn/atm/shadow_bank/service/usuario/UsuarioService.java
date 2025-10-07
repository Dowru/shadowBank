package io.arvn.atm.shadow_bank.service.usuario;

import io.arvn.atm.shadow_bank.model.ClienteDTO;

import java.util.List;

public interface UsuarioService {


    ClienteDTO iniciarSesion(String numeroDocumento, String clave);

    ClienteDTO crearUsuario(ClienteDTO cliente);

    ClienteDTO buscarClientePorId(Integer clienteId);

    ClienteDTO buscarPorClave(String clave);

    ClienteDTO editarUsuario(ClienteDTO cliente);

    void eliminarUsuario(ClienteDTO cliente);

    List<ClienteDTO> obtenerUsuarios();
}
