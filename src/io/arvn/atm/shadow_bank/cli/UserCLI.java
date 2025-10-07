package io.arvn.atm.shadow_bank.cli;

import io.arvn.atm.shadow_bank.core.exception.UsuarioExistenteException;
import io.arvn.atm.shadow_bank.core.exception.UsuarioNoEncontradoException;
import io.arvn.atm.shadow_bank.model.ClienteDTO;
import io.arvn.atm.shadow_bank.model.TipoCuenta;
import io.arvn.atm.shadow_bank.model.cuenta.CuentaDTO;
import io.arvn.atm.shadow_bank.service.usuario.UsuarioService;
import io.arvn.atm.shadow_bank.core.util.ContextUtil;
import io.arvn.atm.shadow_bank.core.util.CuentaUtils;

import java.util.List;

import static io.arvn.atm.shadow_bank.core.util.ConsoleUtil.writeMessage;

public class UserCLI {

    private UsuarioService usuarioService;

    public UserCLI(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public void logout() {
        writeMessage("Gracias por usar Shadow Bank ¡Hasta luego!");
        System.exit(0);
    }

    //private methods
    public Boolean login() {
        String numeroDocumento = CLIUtil.leerLinea("Introduzca el número de documento: ", String.class);
        String clave = CLIUtil.leerLinea("Introduzca su clave: ", String.class);
        try {
            ClienteDTO clienteDTO = usuarioService.iniciarSesion(numeroDocumento, clave);
            ContextUtil.addUserToContext(clienteDTO);
        } catch (UsuarioNoEncontradoException e) {
            writeMessage(e.getMessage());
            return false;

        } finally {
            ClienteDTO clienteContexto = ContextUtil.getClienteContexto();
            System.out.println("Usuario cargado en contexto correctamente: " + clienteContexto);
        }
        return true;
    }

    public void create() throws InterruptedException {
        CLIUtil.mostrarMensaje("\n -- Vamos a Crear tu nuevo usuario!");
        CLIUtil.mostrarMensaje("\n Ten a la mano tu documento de identificación! ");
        CLIUtil.mostrarMensaje("\n Por favor, ingresa la siguiente información para poder empezar nuestro proceso de registro. ");
        String nombre = CLIUtil.leerLinea("Ingrese nombre: ", String.class);
        String documento = CLIUtil.leerLinea("Ingrese documento: ", String.class);
        String clave = CLIUtil.leerLinea("Cree su clave de 4 dígitos: ", String.class);
        CLIUtil.mostrarMensaje("Estamos creando tu usuario para que accedas a nuestros productos.");
        Thread.sleep(1000);

        CLIUtil.mostrarMensaje("Ahora, vamos a configurar tu nuevo producto para completar el registro.");
        String tipoCuentaIngresado = CLIUtil.leerLinea("Selecciona el tipo de cuenta, entre AHORROS y CORRIENTE", String.class).toUpperCase().trim();
        TipoCuenta tipoCuenta = TipoCuenta.resolverTipoCuenta(tipoCuentaIngresado);

        CuentaDTO cuentaDTO = generarCuentaPorDefecto(tipoCuenta);
        ClienteDTO nuevo = generarNuevoCliente(nombre, documento, clave, List.of(cuentaDTO));
        try {
            usuarioService.crearUsuario(nuevo);
            writeMessage(" Usuario creado correctamente: " + nuevo);
        } catch (UsuarioExistenteException e) {
            writeMessage(e.getMessage());
        }
    }


    private static ClienteDTO generarNuevoCliente(String nombre, String documento, String clave, List<CuentaDTO> cuentas) {
        return new ClienteDTO(nombre, documento, clave, cuentas);
    }

    private static CuentaDTO generarCuentaPorDefecto(TipoCuenta tipoCuenta) {
        return new CuentaDTO(CuentaUtils.generateSeed(), 0.0, tipoCuenta);
    }

    public Integer iniciarUserCLI() {
        writeMessage("\n===== Bienvenido a Shadow Bank =====");
        writeMessage("1. Iniciar sesión");
        writeMessage("2. Crear nuevo cliente");
        writeMessage("0. Salir");
        writeMessage("Seleccione una opción: ");
        return CLIUtil.leerLinea("Seleccione una opción: ", Integer.class);
    }

    public void obtenerClientes() {
        List<ClienteDTO> clienteDTOS = usuarioService.obtenerUsuarios();
        clienteDTOS.stream()
                .map(ClienteDTO::toString)
                .forEach(CLIUtil::mostrarMensaje);
    }
}
