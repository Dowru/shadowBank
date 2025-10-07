package io.arvn.atm.shadow_bank.service.transacciones.impl;

import io.arvn.atm.shadow_bank.model.cuenta.CuentaDTO;
import io.arvn.atm.shadow_bank.service.transacciones.AbstractTransaccionService;

import static io.arvn.atm.shadow_bank.core.util.ConsoleUtil.writeMessage;

public class TransaccionCuentaAhorrosService extends AbstractTransaccionService {

    @Override
    public void retirar(double monto) {
        if (monto > 0) {
            CuentaDTO cuenta = getCuenta();
            double saldo = cuenta.getSaldo();
            cuenta.setSaldo(saldo - monto);
            this.setCuenta(cuenta);
            writeMessage("Retiro exitoso \n Nuevo saldo: " + cuenta.getSaldo());
        } else {
            writeMessage("No puedes retirar esa cantidad porque debes mantener un saldo mayor a" + monto);
        }
    }
}
