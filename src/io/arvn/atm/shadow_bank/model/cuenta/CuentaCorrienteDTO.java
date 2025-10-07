package io.arvn.atm.shadow_bank.model.cuenta;

import io.arvn.atm.shadow_bank.model.TipoCuenta;

public class CuentaCorrienteDTO extends CuentaDTO{

    private Double sobreCupo;

    public CuentaCorrienteDTO(String numeroCuenta, double saldo, Double sobreCupo) {
        super(numeroCuenta, saldo, TipoCuenta.CORRIENTE);
        this.sobreCupo = sobreCupo;

    }

    public CuentaCorrienteDTO(Integer id, String numeroCuenta, double saldo, Double sobreCupo) {
        super(id, numeroCuenta, saldo, TipoCuenta.CORRIENTE);
        this.sobreCupo = sobreCupo;
    }

    public Double getSobreCupo() {
        return sobreCupo;
    }

    public void setSobreCupo(Double sobreCupo) {
        this.sobreCupo = sobreCupo;
    }
}
