package io.arvn.atm.shadow_bank.model;

public enum TipoCuenta {
    AHORROS, CORRIENTE;

    public static TipoCuenta resolverTipoCuenta(String tipoCuenta) {
        return TipoCuenta.valueOf(tipoCuenta.toUpperCase());
    }
}
