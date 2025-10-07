package io.arvn.atm.shadow_bank.model.cuenta;

import io.arvn.atm.shadow_bank.model.TipoCuenta;

public class CuentaDTO {
    private Integer id;
    private String numeroCuenta;
    private TipoCuenta tipoCuenta;
    private double saldo;

    public CuentaDTO(String numeroCuenta, double saldo, TipoCuenta tipoCuenta) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.tipoCuenta = tipoCuenta;
    }

    public CuentaDTO(Integer id, String numeroCuenta, double saldo, TipoCuenta tipoCuenta) {
        this.id = id;
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.tipoCuenta = tipoCuenta;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "CuentaDTO{" +
                "id=" + id +
                ", numeroCuenta='" + numeroCuenta + '\'' +
                ", tipoCuenta=" + tipoCuenta +
                ", saldo=" + saldo +
                '}';
    }
}
