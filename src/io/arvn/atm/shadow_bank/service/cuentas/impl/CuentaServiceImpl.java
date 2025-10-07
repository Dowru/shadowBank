package io.arvn.atm.shadow_bank.service.cuentas.impl;

import io.arvn.atm.shadow_bank.core.util.ContextUtil;
import io.arvn.atm.shadow_bank.model.cuenta.CuentaDTO;
import io.arvn.atm.shadow_bank.repository.CuentasRepository;
import io.arvn.atm.shadow_bank.service.cuentas.CuentaService;

import java.util.List;

public class CuentaServiceImpl implements CuentaService {
    private CuentasRepository cuentasRepository;

    public CuentaServiceImpl(CuentasRepository cuentasRepository) {
        this.cuentasRepository = cuentasRepository;
    }

    @Override
    public CuentaDTO create(CuentaDTO cuenta) {
        return cuentasRepository.create(cuenta);
    }

    @Override
    public CuentaDTO find(int id) {
        return cuentasRepository.getCuenta(id);
    }

    @Override
    public CuentaDTO update(CuentaDTO cuenta) {
        return cuentasRepository.edit(cuenta);
    }

    @Override
    public List<CuentaDTO> findAll() {
        return cuentasRepository.getCuentas();
    }
}
