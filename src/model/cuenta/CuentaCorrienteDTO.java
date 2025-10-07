package model.cuenta;

public class CuentaCorrienteDTO extends CuentaDTO{

    private Double sobreCupo;

    public CuentaCorrienteDTO(String numeroCuenta, double saldo, Double sobreCupo) {
        super(numeroCuenta, saldo);
        this.sobreCupo = sobreCupo;

    }

    public Double getSobreCupo() {
        return sobreCupo;
    }

    public void setSobreCupo(Double sobreCupo) {
        this.sobreCupo = sobreCupo;
    }
}
