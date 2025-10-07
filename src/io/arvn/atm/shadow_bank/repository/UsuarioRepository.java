package io.arvn.atm.shadow_bank.repository;

import io.arvn.atm.shadow_bank.core.exception.CajeroException;
import io.arvn.atm.shadow_bank.model.ClienteDTO;

import java.util.List;

public class UsuarioRepository {


    public static Integer id = 0;
    private List<ClienteDTO> usuarios;

    public UsuarioRepository(List<ClienteDTO> usuarios) {
        this.usuarios = usuarios;
    }

    public List<ClienteDTO> getUsuarios() {
        return usuarios;
    }

    public ClienteDTO getUsuario(Integer id) {
        return usuarios.stream().filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(()-> new CajeroException("No existe el cliente por id " + id));    }

    public ClienteDTO create(ClienteDTO cliente) {
        cliente.setId(generateId());
        this.usuarios.add(cliente);
        return cliente;
    }

    public ClienteDTO edit(ClienteDTO cliente) {
        var usuario = getUsuario(cliente.getId());
        usuario.setId(cliente.getId());
        usuario.setNombre(cliente.getNombre());
        usuario.setDocumento(cliente.getDocumento());
        this.usuarios.set(cliente.getId(), usuario);
        return usuario;
    }

    public void delete(ClienteDTO cliente) {
        this.usuarios.remove(cliente);
    }

    private static Integer generateId(){
        id++;
        return id;
    }
}
