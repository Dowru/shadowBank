package model;

import model.cuenta.CuentaDTO;

public class ClienteDTO {

    // Atributos miembro de Clientes
    private String nombre;
    private String documento;
    private final String clave;
    private final CuentaDTO cuenta;

    // Contrusctor de cliente

    public ClienteDTO(String nombre, String documento, String clave, CuentaDTO cuenta) {
        this.nombre = nombre;
        this.documento = documento;
        this.clave = clave;
        this.cuenta = cuenta;
    }

    // GETTERS Y SETTERS

    public String getNombre() {
        return nombre;
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

    public CuentaDTO getCuenta() {
        return cuenta;
    }

    // Metodo para mostrar informacion personal

    public void mostrarInformacion() {
        System.out.println("\nCliente: " + nombre);
        System.out.println("\nDocumento: " + documento);
        System.out.println("Clave: " + clave);
    }
}
