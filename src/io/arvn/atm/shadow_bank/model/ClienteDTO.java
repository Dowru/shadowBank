package io.arvn.atm.shadow_bank.model;

import io.arvn.atm.shadow_bank.model.cuenta.CuentaDTO;

import java.util.List;

public class ClienteDTO {

    private Integer id;
    // Atributos miembro de Clientes
    private String nombre;
    private String documento;
    private String clave;
    private final List<CuentaDTO> cuentas;

    // Contrusctor de cliente

    public ClienteDTO(String nombre, String documento, String clave, List<CuentaDTO> cuentas) {
        this.nombre = nombre;
        this.documento = documento;
        this.clave = clave;
        this.cuentas = cuentas;
    }

    public ClienteDTO(Integer id, String nombre, String documento, String clave, List<CuentaDTO> cuentas) {
        this.id = id;
        this.nombre = nombre;
        this.documento = documento;
        this.clave = clave;
        this.cuentas = cuentas;
    }


    // GETTERS Y SETTERS

    public String getNombre() {
        return nombre;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getClave() {
        return clave;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<CuentaDTO> getCuentas() {
        return cuentas;
    }

    @Override
    public String toString() {
        return "ClienteDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", documento='" + documento + '\'' +
                ", clave='" + clave + '\'' +
                ", cuenta=" + cuentas +
                '}';
    }
}
