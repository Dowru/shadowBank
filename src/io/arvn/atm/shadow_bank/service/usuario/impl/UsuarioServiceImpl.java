package io.arvn.atm.shadow_bank.service.usuario.impl;

import io.arvn.atm.shadow_bank.core.exception.UsuarioExistenteException;
import io.arvn.atm.shadow_bank.core.exception.UsuarioNoEncontradoException;
import io.arvn.atm.shadow_bank.model.ClienteDTO;
import io.arvn.atm.shadow_bank.repository.UsuarioRepository;
import io.arvn.atm.shadow_bank.service.usuario.UsuarioService;

import java.util.List;
import java.util.Objects;

public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public ClienteDTO iniciarSesion(String numeroDocumento, String clave){
        return usuarioRepository.getUsuarios()
                .stream()
                .filter(Objects::nonNull)
                .filter(cliente -> cliente.getClave().equals(clave) && cliente.getDocumento().equals(numeroDocumento))
                .findFirst().orElseThrow(()-> new UsuarioNoEncontradoException("Usuario no encontrado"));
    }


    @Override
    public ClienteDTO crearUsuario(ClienteDTO cliente) {
        var existe = usuarioRepository.getUsuarios()
                .stream()
                .anyMatch(c -> c.getDocumento().equals(cliente.getDocumento()));
        if (existe) throw new UsuarioExistenteException("Usuario ya existente");
        return usuarioRepository.create(cliente);
    }

    @Override
    public ClienteDTO buscarClientePorId(Integer clienteId) {
        return usuarioRepository.getUsuario(clienteId);
    }

    @Override
    public ClienteDTO buscarPorClave(String clave) {
        for (ClienteDTO cliente : usuarioRepository.getUsuarios()) {
            if (cliente.getClave().equals(clave)) {
                return cliente;
            }
        }
        return null;
    }

    @Override
    public ClienteDTO editarUsuario(ClienteDTO cliente) {
        return usuarioRepository.edit(cliente);
    }

    @Override
    public void eliminarUsuario(ClienteDTO cliente) {
        ClienteDTO usuario = usuarioRepository.getUsuario(cliente.getId());
        usuarioRepository.delete(usuario);
    }

    @Override
    public List<ClienteDTO> obtenerUsuarios() {
        return usuarioRepository.getUsuarios();
    }
}
