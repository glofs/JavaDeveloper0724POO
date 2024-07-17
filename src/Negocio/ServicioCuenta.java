package Negocio;

import java.util.ArrayList;

public interface ServicioCuenta {
    boolean agregarCuenta(Cuenta cuenta);

    boolean cancelarCuenta(int numero);

    void abonarCuenta(int numero, double abono);
    void retiroCuenta(int numero, double abono)
;
    ArrayList<Cuenta> obtenerCuentas();

    void listarCuentas();
    Cuenta bucarCuentas(int numero);
}
