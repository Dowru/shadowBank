package io.arvn.atm.shadow_bank.service.cuentas;

import io.arvn.atm.shadow_bank.model.cuenta.CuentaDTO;

import java.util.List;

public interface CuentaService {
    CuentaDTO create(CuentaDTO cuenta);
    CuentaDTO find(int id);
    CuentaDTO update(CuentaDTO cuenta);
    List<CuentaDTO> findAll();
}
