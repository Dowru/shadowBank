package io.arvn.atm.shadow_bank.model.cuenta;

import io.arvn.atm.shadow_bank.model.TipoCuenta;

public class CuentaAhorrosDTO extends CuentaDTO{

    public CuentaAhorrosDTO(String numeroCuenta, double saldo) {
        super(numeroCuenta, saldo, TipoCuenta.AHORROS);
    }

    public CuentaAhorrosDTO(Integer id, String numeroCuenta, double saldo) {
        super(id, numeroCuenta, saldo, TipoCuenta.AHORROS);
    }
}
