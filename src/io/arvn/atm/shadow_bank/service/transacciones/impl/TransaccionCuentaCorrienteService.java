package io.arvn.atm.shadow_bank.service.transacciones.impl;

import io.arvn.atm.shadow_bank.model.cuenta.CuentaCorrienteDTO;
import io.arvn.atm.shadow_bank.service.transacciones.AbstractTransaccionService;

import static io.arvn.atm.shadow_bank.core.util.ConsoleUtil.writeMessage;

public class TransaccionCuentaCorrienteService extends AbstractTransaccionService {

    @Override
    public void retirar(double monto) {
        CuentaCorrienteDTO cuenta = (CuentaCorrienteDTO) getCuenta();
        if (monto > 0 && monto < cuenta.getSobreCupo()) {
            double saldo = cuenta.getSaldo();
            cuenta.setSaldo(saldo - monto);
            this.setCuenta(cuenta);
            writeMessage("Retiro exitoso \n Nuevo saldo: " + cuenta.getSaldo());
        } else {
            writeMessage("No puedes retirar esa cantidad porque debes mantener un saldo mayor a" + monto);
        }
    }

    @Override
    public Double consultarSaldo() {
        double saldo = getCuenta().getSaldo();
        return saldo - (saldo * 0.04);
    }
}
