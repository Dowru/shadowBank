package io.arvn.atm.shadow_bank.cli;

import io.arvn.atm.shadow_bank.core.exception.CajeroException;
import io.arvn.atm.shadow_bank.model.ClienteDTO;
import io.arvn.atm.shadow_bank.model.TipoCuenta;
import io.arvn.atm.shadow_bank.model.cuenta.CuentaDTO;
import io.arvn.atm.shadow_bank.service.transacciones.TransaccionService;
import io.arvn.atm.shadow_bank.service.usuario.UsuarioService;
import io.arvn.atm.shadow_bank.core.util.ContextUtil;

import java.util.List;
import java.util.Map;

import static io.arvn.atm.shadow_bank.core.util.ConsoleUtil.writeMessage;

public class TransaccionCLI {

    private Map<TipoCuenta, TransaccionService> transaccionServiceStategies;
    private TransaccionService currentTransactionService;
    private UsuarioService usuarioService;

    public TransaccionCLI(Map<TipoCuenta, TransaccionService> transaccionService, UsuarioService usuarioService) {
        this.transaccionServiceStategies = transaccionService;
        this.usuarioService = usuarioService;
    }

    public Integer iniciarMenuCustomer() {
        ClienteDTO clienteContexto = ContextUtil.getClienteContexto();
        if (clienteContexto == null) throw new CajeroException("Usuario no ha iniciado sesión.");

        writeMessage("\n ===== MENU PRINCIPAL =====");
        writeMessage("1. Consultar Saldo ");
        writeMessage("2. Depositar ");
        writeMessage("3. Retirar ");
        writeMessage("4. Consultar datos personales ");
        writeMessage("5. Editar datos personales ");
        writeMessage("6. Eliminar usuario ");
        writeMessage("0. Salir ");
        return CLIUtil.leerLinea("Seleccione una opción: ", Integer.class);
    }

    public void consultarSaldo() {
        currentTransactionService.consultarSaldo();
    }

    public void depositar() {
        CLIUtil.mostrarMensaje("=== Depositar ====");
        Double monto = CLIUtil.leerLinea("Ingrese el monto a depositar: ", Double.class);
        String cuenta = CLIUtil.leerLinea("Ingrese el número de cuenta: ", String.class);
        ClienteDTO clienteContexto = ContextUtil.getClienteContexto();

        ClienteDTO cliente = usuarioService.buscarClientePorId(clienteContexto.getId());
        List<CuentaDTO> cuentas = cliente.getCuentas();
        if(cuentas.isEmpty()) throw new CajeroException("Este usuario no tiene cuentas.");

        var cuentaCliente = cuentas.stream()
                .filter(cuentaDisponible -> cuentaDisponible.getNumeroCuenta().equals(cuenta))
                .findFirst().orElseThrow(() -> new CajeroException("No existe el cuenta con el número de cuenta " + cuenta));

        TipoCuenta tipoCuenta = cuentaCliente.getTipoCuenta();
        TransaccionService transaccionService = resolverEstrategia(tipoCuenta);
        transaccionService.setCuenta(cuentaCliente);
        transaccionService.depositar(monto);
    }

    public void retirar() {
        CLIUtil.mostrarMensaje("=== Retirar ====");
        Double monto = CLIUtil.leerLinea("Ingrese el monto a retirar: ", Double.class);
        String cuenta = CLIUtil.leerLinea("Ingrese el número de cuenta: ", String.class);
        ClienteDTO clienteContexto = ContextUtil.getClienteContexto();

        ClienteDTO cliente = usuarioService.buscarClientePorId(clienteContexto.getId());
        List<CuentaDTO> cuentas = cliente.getCuentas();
        if(cuentas.isEmpty()) throw new CajeroException("Este usuario no tiene cuentas.");

        var cuentaCliente = cuentas.stream()
                .filter(cuentaDisponible -> cuentaDisponible.getNumeroCuenta().equals(cuenta))
                .findFirst().orElseThrow(() -> new CajeroException("No existe el cuenta con el número de cuenta " + cuenta));

        TipoCuenta tipoCuenta = cuentaCliente.getTipoCuenta();
        TransaccionService transaccionService = resolverEstrategia(tipoCuenta);
        transaccionService.setCuenta(cuentaCliente);
        transaccionService.retirar(monto);
    }

    public void mostrarInformacion() {
        CLIUtil.mostrarMensaje("=== Mostrar Informacion ====");
        CLIUtil.mostrarMensaje(ContextUtil.getClienteContexto().toString());
    }

    public void editarInformacion() {
        CLIUtil.mostrarMensaje("=== Editar Informacion ====");
        CLIUtil.mostrarMensaje(ContextUtil.getClienteContexto().toString());

        String nombre = CLIUtil.leerLinea("Ingrese el nuevo nombre: ", String.class);
        String documento = CLIUtil.leerLinea("Ingrese el nuevo documento: ", String.class);
        String clave = CLIUtil.leerLinea("Ingrese el nuevo clave: ", String.class);


        ClienteDTO clienteContexto = ContextUtil.getClienteContexto();

        clienteContexto.setNombre(nombre);
        clienteContexto.setDocumento(documento);
        clienteContexto.setClave(clave);
        usuarioService.editarUsuario(clienteContexto);
        ContextUtil.finalizeContext();
        ContextUtil.addUserToContext(clienteContexto);
    }

    public void eliminarCliente() {
        //usuarioService.eliminarUsuario();

    }

    public TransaccionService resolverEstrategia(TipoCuenta tipoCuenta) {
        return transaccionServiceStategies.get(tipoCuenta);
    }

}
