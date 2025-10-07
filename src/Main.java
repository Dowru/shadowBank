import model.ClienteDTO;
import model.TipoCuenta;
import model.cuenta.CuentaAhorrosDTO;
import model.cuenta.CuentaCorrienteDTO;
import model.cuenta.CuentaDTO;
import service.Cajero;
import service.TransaccionService;
import service.impl.TransaccionCuentaAhorrosService;
import service.impl.TransaccionCuentaCorrienteService;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

// se crea un arraylist para agregar algunos clientes por determinado y con el constructor de cajero se agregan al array list de este mismo

        List<ClienteDTO> clienteDTOS = new LinkedList<>();

        var cuenta1 = new CuentaAhorrosDTO("A001", 0);
        var cuenta2 = new CuentaAhorrosDTO("A002", 0);
        var cuenta3 = new CuentaAhorrosDTO("A003", 0);
        var cuenta4 = new CuentaCorrienteDTO("C001", 1000000, 1000000.0);
        var cuenta5 = new CuentaCorrienteDTO("C002", 1000000, 1000000.0);

        clienteDTOS.add(new ClienteDTO("Santiago Gomez", "12345678", "7897", cuenta1));
        clienteDTOS.add(new ClienteDTO("Laura Pérez", "87654321", "1576", cuenta2));
        clienteDTOS.add(new ClienteDTO("Carlos Ruiz", "23456789", "3839", cuenta3));
        clienteDTOS.add(new ClienteDTO("Valentina Mora", "98765432", "1028", cuenta4));
        clienteDTOS.add(new ClienteDTO("Andrés López", "34567890", "4028", cuenta5));

        // crea un objeto service.Cajero y le pasa la lista de clientes al constructor

        var ahorrosTransaccionService = new TransaccionCuentaAhorrosService();
        var corrienteTransaccionService = new TransaccionCuentaCorrienteService();

        Map<TipoCuenta, TransaccionService> transaccionService = new HashMap<>();
        transaccionService.put(TipoCuenta.AHORROS, ahorrosTransaccionService);
        transaccionService.put(TipoCuenta.CORRIENTE, corrienteTransaccionService);

        var cajero = new Cajero(clienteDTOS,  transaccionService);
        cajero.iniciar();
    }
}
