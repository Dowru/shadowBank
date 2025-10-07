package service;

import model.cuenta.CuentaDTO;

import java.lang.classfile.constantpool.DoubleEntry;

public abstract class TransaccionServiceImpl implements TransaccionService {

    // Atributos miembro de cuenta

    private CuentaDTO cuenta;

    // constructor de cuenta

    public TransaccionServiceImpl() {
    }
    // sobre escritura de los metodos de interfaz

    // en depositar primero valida que el valor de monto no sea un numero negativo y despues establece
    // el nuevo valor de saldo con el mismo valor de monto

    @Override
    public void depositar(double monto) {
        if (monto > 0) {
            double saldo = cuenta.getSaldo();
            cuenta.setSaldo(saldo + monto);
            System.out.println("Depósito exitoso. Nuevo saldo: " + saldo);
        } else {
            System.out.println("El monto debe ser mayor a 0.");
        }
    }

    // en retirar valida que monto no sea un valor negativo o que el saldo no sea menor al monto que sea desea
    // retirar con un compárador logico

    @Override
    public abstract void retirar(double monto);

    // metodo consultar saldo que es el valor del atributo de esta clase

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
