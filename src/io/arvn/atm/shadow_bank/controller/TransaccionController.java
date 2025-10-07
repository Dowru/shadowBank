package io.arvn.atm.shadow_bank.controller;

import io.arvn.atm.shadow_bank.cli.TransaccionCLI;

import static io.arvn.atm.shadow_bank.core.util.ConsoleUtil.writeMessage;

public class TransaccionController {
    
    private final TransaccionCLI transaccionCLI;

    public TransaccionController(TransaccionCLI transaccionCLI) {
        this.transaccionCLI = transaccionCLI;
    }

    public void selectOption() throws InterruptedException {
        int option;
        do {

            option = transaccionCLI.iniciarMenuCustomer();
            switch (option) {
                case 1 -> transaccionCLI.consultarSaldo();
                case 2 -> transaccionCLI.depositar();
                case 3 -> transaccionCLI.retirar();
                case 4 -> transaccionCLI.mostrarInformacion();
                case 5 -> transaccionCLI.editarInformacion();
                case 6 -> transaccionCLI.eliminarCliente();

                default -> writeMessage("Opción inválida. Intente de nuevo.");

            }
        } while (option != 0);
    }
}
