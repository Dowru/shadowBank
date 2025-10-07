package service;

import model.cuenta.CuentaDTO;

public interface TransaccionService {

    void depositar(double monto);

    void retirar(double monto);

    Double consultarSaldo();

    CuentaDTO getCuenta();
    void setCuenta(CuentaDTO cuenta);
}

// interfaz con dos metodos vacios