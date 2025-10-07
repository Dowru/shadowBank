package io.arvn.atm.shadow_bank.service.transacciones;

import io.arvn.atm.shadow_bank.model.cuenta.CuentaDTO;

public interface TransaccionService {

    void depositar(double monto);

    void retirar(double monto);

    Double consultarSaldo();

    CuentaDTO getCuenta();
    void setCuenta(CuentaDTO cuenta);
}

// interfaz con dos metodos vacios