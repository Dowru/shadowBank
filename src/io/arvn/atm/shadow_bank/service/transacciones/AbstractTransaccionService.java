package io.arvn.atm.shadow_bank.service.transacciones;

import io.arvn.atm.shadow_bank.model.cuenta.CuentaDTO;

import static io.arvn.atm.shadow_bank.core.util.ConsoleUtil.writeMessage;

public abstract class AbstractTransaccionService implements TransaccionService {

    private CuentaDTO cuenta;

    protected AbstractTransaccionService() {
    }

    @Override
    public void depositar(double monto) {
        if (monto > 0) {
            double saldo = cuenta.getSaldo();
            cuenta.setSaldo(saldo + monto);
            writeMessage("Depósito exitoso. Nuevo saldo: " + cuenta.getSaldo());
        } else {
            writeMessage("El monto debe ser mayor a 0.");
        }
    }

    public Double consultarSaldo() {
        return cuenta.getSaldo();
    }

    public CuentaDTO getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaDTO cuenta) {
        this.cuenta = cuenta;
    }
}
