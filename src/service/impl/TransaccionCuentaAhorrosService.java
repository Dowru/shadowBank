package service.impl;

import model.cuenta.CuentaDTO;
import service.TransaccionServiceImpl;

import static util.ConsoleUtil.writeMessage;

public class TransaccionCuentaAhorrosService extends TransaccionServiceImpl {

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
