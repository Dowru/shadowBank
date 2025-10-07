package service;

import model.ClienteDTO;
import model.TipoCuenta;
import model.cuenta.CuentaAhorrosDTO;
import model.cuenta.CuentaCorrienteDTO;
import model.cuenta.CuentaDTO;
import util.ConsoleUtil;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static util.ConsoleUtil.writeMessage;

public class Cajero {

    // atrubutos miembro de cajero

    private List<ClienteDTO> clienteDTOS;
    private final Map<TipoCuenta, TransaccionService> transaccionService;
    private Scanner scanner;

    // constructor de cajero

    public Cajero(List<ClienteDTO> clienteDTOS, Map<TipoCuenta, TransaccionService> transaccionService) {
        this.clienteDTOS = clienteDTOS;
        this.transaccionService = transaccionService;
        this.scanner = new Scanner(System.in);
    }

    // metodo iniciar donde con un bucle do while se crea una interfaz para el usuario

    public void iniciar() {
        int opcion;
        do {
            System.out.println("\n===== Bienvenido a Shadow Bank =====");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Crear nuevo cliente");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> iniciarSesion();
                case 2 -> crearCliente();
                case 0 -> System.out.println("Gracias por usar Shadow Bank. ¡Hasta luego!");
                case 99 -> mostrarClientes();
                default -> System.out.println("Opción inválida. Intente de nuevo.");
            }

        } while (opcion != 0);
    }

    // metodo para iniciar sesion que compara con otro metodo si la clave ingresada por el usuario es igual a una dentro del arraylist

    private void iniciarSesion() {
        System.out.print("Ingrese su clave: ");
        String clave = scanner.nextLine();

        ClienteDTO clienteDTO = buscarClientePorClave(clave);

        // condicional donde si el resultado de la busqueda concuerda con un cliente le da la bienvenida y llama la siguiente interfaz con otro metodo

        if (clienteDTO != null) {
            System.out.println("Bienvenido, " + clienteDTO.getNombre() + "!");
            menuCliente(clienteDTO);

            // de lo contrario le dira que la clave es incorrecta
        } else {
            System.out.println("Clave incorrecta");
        }
    }

    // metodo do while con el fin de presentar una interfaz a un cliente ya registrado

    private void menuCliente(ClienteDTO clienteDTO) {
        int opcion;
        do {
            System.out.println("\n--- Menú del cliente ---");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Depositar dinero");
            System.out.println("3. Retirar dinero");
            System.out.println("4. Editar datos personales");
            System.out.println("5. Mostrar mi información");
            System.out.println("6. Eliminar mi cuenta");
            System.out.println("0. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                // va al metodo que se encuentra dentro de la clase cuenta "Consultar saldo"
                case 1 -> {
                    var cuenta = clienteDTO.getCuenta();
                    if(cuenta instanceof CuentaAhorrosDTO) {
                        consultarSaldo(TipoCuenta.AHORROS, cuenta);
                    }else if(cuenta instanceof CuentaCorrienteDTO) {
                        consultarSaldo(TipoCuenta.CORRIENTE, cuenta);
                    }
                }

                // va al metodo de depositar dentro de la clase cuenta para agregar su nuevo valor a la cuenta llamada con el getter
                case 2 -> {
                      writeMessage("Ingrese el monto a depositar: ");
//                    clienteDTO.getCuenta().depositar(scanner.nextDouble());
                }
                // va al metodo de depositar dentro de la clase cuenta y le agrega su nuevo valor a la cuenta llamada con el getter
                case 3 -> {
                    System.out.print("Ingrese el monto a retirar: ");
                    //clienteDTO.getCuenta().retirar(scanner.nextDouble());
                }

                // con el metodo editarCliente permite modificar el usuario actual llamado del array

                case 4 -> editarCliente(clienteDTO);

                // va al metodo de mostrar informacion dentro de la clase cliente para mostrar los datos principales
                case 5 -> clienteDTO.mostrarInformacion();

                // va al metodo eliminarCliente que pérmite eliminar el usuario actual llamado del array
                case 6 -> eliminarClientePropio(clienteDTO);
                case 0 -> System.out.println("Finalizar sesion");
                default -> System.out.println("Opción inválida");
            }

        } while (opcion != 0);
    }

    private void consultarSaldo(TipoCuenta ahorros, CuentaDTO cuenta) {
        var corrienteTransaccionService = transaccionService.get(ahorros);
        corrienteTransaccionService.setCuenta(cuenta);
        Double saldo = corrienteTransaccionService.consultarSaldo();
        ConsoleUtil.writeMessage("El saldo actual de la cuenta " + cuenta.getNumeroCuenta() + " es: " + saldo);
    }

    private void crearCliente() {
        System.out.print("Ingrese nombre del cliente: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese documento del cliente: ");
        String documento = scanner.nextLine();
        System.out.print("Cree una clave de acceso de 4 digitos (no se podrá cambiar): ");
        String clave = scanner.nextLine();

        System.out.print("Tipo de cuenta (1 = Ahorros / 2 = Corriente): ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        // con un condicional al seleccionar 1 o 2 escoje el tipo de cuenta que desea crear

        TransaccionServiceImpl transaccionServiceImpl;
        if (tipo == 1) {
            //transaccionServiceImpl = new CuentaAhorros("A00" + (clienteDTOS.size() + 1), 0);
        } else {
            //transaccionServiceImpl = new CuentaCorriente("C00" + (clienteDTOS.size() + 1), 1_000_000, 1_000_000);
        }

        //clienteDTOS.add(new ClienteDTO(nombre, documento, clave, transaccionServiceImpl));

        System.out.println("Cliente creado correctamente ✅");
    }

    // con los getter y setter dentro de la clase cliente edita el nombre y el documente

    private void editarCliente(ClienteDTO clienteDTO) {
        System.out.print("Nuevo nombre (actual: " + clienteDTO.getNombre() + "): ");
        clienteDTO.setNombre(scanner.nextLine());
        System.out.print("Nuevo documento (actual: " + clienteDTO.getDocumento() + "): ");
        clienteDTO.setDocumento(scanner.nextLine());
        System.out.println("Datos actualizados correctamente ✅");
    }
// con el metodo por defecto elimina el cliente de la sesion actual tomado del array

    private void eliminarClientePropio(ClienteDTO clienteDTO) {
        clienteDTOS.remove(clienteDTO);
        System.out.println("Tu cuenta ha sido eliminada del sistema. ✅");
    }

    // con un ciclo y condicional busca que la clave ingresada del cliente sea igual a la clave de un cliente dentro del array

    private ClienteDTO buscarClientePorClave(String clave) {
        for (ClienteDTO clienteDTO : clienteDTOS) {
            if (clienteDTO.getClave().equals(clave)) {
                return clienteDTO;
            }
        }
        return null;
    }

    // con un ciclo que recorre tod0 el array le aplica el metodo de mostrar informacion para cada uno

    private void mostrarClientes() {
        System.out.println("\n--- Lista de clientes ---");
        for (ClienteDTO clienteDTO : clienteDTOS) {
            clienteDTO.mostrarInformacion();
        }
    }
}
