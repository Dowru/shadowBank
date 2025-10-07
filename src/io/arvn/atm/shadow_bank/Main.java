package io.arvn.atm.shadow_bank;

import io.arvn.atm.shadow_bank.cli.TransaccionCLI;
import io.arvn.atm.shadow_bank.cli.UserCLI;
import io.arvn.atm.shadow_bank.controller.TransaccionController;
import io.arvn.atm.shadow_bank.controller.UserController;
import io.arvn.atm.shadow_bank.model.ClienteDTO;
import io.arvn.atm.shadow_bank.model.TipoCuenta;
import io.arvn.atm.shadow_bank.model.cuenta.CuentaAhorrosDTO;
import io.arvn.atm.shadow_bank.model.cuenta.CuentaCorrienteDTO;
import io.arvn.atm.shadow_bank.repository.UsuarioRepository;
import io.arvn.atm.shadow_bank.service.transacciones.TransaccionService;
import io.arvn.atm.shadow_bank.service.usuario.UsuarioService;
import io.arvn.atm.shadow_bank.service.usuario.impl.UsuarioServiceImpl;
import io.arvn.atm.shadow_bank.service.transacciones.impl.TransaccionCuentaAhorrosService;
import io.arvn.atm.shadow_bank.service.transacciones.impl.TransaccionCuentaCorrienteService;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException, InterruptedException {

        List<ClienteDTO> clienteDTOS = new LinkedList<>();

        var cuenta1 = new CuentaAhorrosDTO(1, "A001", 0);
        var cuenta2 = new CuentaAhorrosDTO(2, "A002", 100000);
        var cuenta3 = new CuentaAhorrosDTO(3, "A003", 0);
        var cuenta4 = new CuentaAhorrosDTO(4, "A004", 0);
        var cuenta5 = new CuentaAhorrosDTO(5, "A005", 100000);
        var cuenta6 = new CuentaCorrienteDTO(6, "C001", 1000000, 1000000.0);
        var cuenta7 = new CuentaCorrienteDTO(7, "C002", 1000000, 1000000.0);
        var cuenta8 = new CuentaCorrienteDTO(8, "C002", 1000000, 1000000.0);
        var cuenta9 = new CuentaCorrienteDTO(9, "C002", 1000000, 1000000.0);

        clienteDTOS.add(new ClienteDTO(1,"Santiago Gomez", "12345678", "7897", List.of(cuenta1)));
        clienteDTOS.add(new ClienteDTO(2,"Laura Pérez", "87654321", "1576", List.of(cuenta2)));
        clienteDTOS.add(new ClienteDTO(3,"Jorge Alejandro", "23456789", "3839", List.of(cuenta3, cuenta6, cuenta7, cuenta8, cuenta9)));
        clienteDTOS.add(new ClienteDTO(4,"Valentina Mora", "98765432", "1028", List.of(cuenta4)));
        clienteDTOS.add(new ClienteDTO(5,"Andrés López", "34567890", "4028", List.of(cuenta5)));

        //crear objetos con dependencias
        var ahorrosTransaccionService = new TransaccionCuentaAhorrosService();
        var corrienteTransaccionService = new TransaccionCuentaCorrienteService();

        //repositories
        UsuarioRepository usuarioRepository = new UsuarioRepository(clienteDTOS);

        //servicios
        UsuarioService usuarioService = new UsuarioServiceImpl(usuarioRepository);
        Map<TipoCuenta, TransaccionService> transaccionService = new HashMap<>();
        transaccionService.put(TipoCuenta.AHORROS, ahorrosTransaccionService);
        transaccionService.put(TipoCuenta.CORRIENTE, corrienteTransaccionService);

        //cli
        UserCLI userCLI = new UserCLI(usuarioService);
        TransaccionCLI transaccionCLI = new TransaccionCLI(transaccionService, new UsuarioServiceImpl(usuarioRepository));

        //controllers
        UserController userController = new UserController(userCLI);
        TransaccionController transaccionController = new TransaccionController(transaccionCLI);
        var continua = userController.selectOption();
        if (continua) {
            transaccionController.selectOption();
        }
    }
}
