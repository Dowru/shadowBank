package io.arvn.atm.shadow_bank.controller;

import io.arvn.atm.shadow_bank.cli.UserCLI;

import static io.arvn.atm.shadow_bank.core.util.ConsoleUtil.writeMessage;

public class UserController {


    private final UserCLI userCLI;

    public UserController(UserCLI userCLI) {
        this.userCLI = userCLI;
    }

    public boolean selectOption() throws InterruptedException {
        int opcion = 999999999;
        boolean siguienteFlujo = false;
        while (opcion != 0 || !siguienteFlujo) {

            opcion = userCLI.iniciarUserCLI();
            switch (opcion) {
                case 1 -> {
                    siguienteFlujo = userCLI.login();
                    if(siguienteFlujo) opcion = 0;
                }
                case 2 -> userCLI.create();
                case 0 -> userCLI.logout();
                case 99 -> userCLI.obtenerClientes();

                default -> writeMessage("Opción inválida. Intente de nuevo.");
            }

        }
        return siguienteFlujo;
    }

}
