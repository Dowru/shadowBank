package io.arvn.atm.shadow_bank.repository;

import io.arvn.atm.shadow_bank.core.exception.CajeroException;
import io.arvn.atm.shadow_bank.model.cuenta.CuentaDTO;

import java.util.List;

public class CuentasRepository {

    public static Integer id = 0;
    private List<CuentaDTO> cuentas;

    public CuentasRepository(List<CuentaDTO> cuentas) {
        this.cuentas = cuentas;
    }

    public List<CuentaDTO> getCuentas() {
        return cuentas;
    }

    public CuentaDTO getCuenta(Integer id) {
        return cuentas.stream().filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new CajeroException("No existe el cliente por id " + id));
    }

    public CuentaDTO create(CuentaDTO cliente) {
        cliente.setId(generateId());
        this.cuentas.add(cliente);
        return cliente;
    }

    public CuentaDTO edit(CuentaDTO cuentaNuevosDatos) {
        var cuenta = getCuenta(cuentaNuevosDatos.getId());
        cuenta.setId(cuentaNuevosDatos.getId());
        cuenta.setNumeroCuenta(cuentaNuevosDatos.getNumeroCuenta());
        cuenta.setTipoCuenta(cuentaNuevosDatos.getTipoCuenta());
        this.cuentas.set(cuentaNuevosDatos.getId(), cuenta);
        return cuenta;
    }

    public void delete(CuentaDTO cliente) {
        this.cuentas.remove(cliente);
    }

    private static Integer generateId() {
        id++;
        return id;
    }
}
