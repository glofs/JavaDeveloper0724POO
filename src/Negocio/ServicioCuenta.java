package Negocio;

import java.util.ArrayList;
import java.util.Optional;

public interface ServicioCuenta {
    boolean agregarCuenta(Cuenta cuenta);

    boolean cancelarCuenta(int numero);

    void abonarCuenta(int numero, double abono);
    void retiroCuenta(int numero, double abono)
;
    ArrayList<Cuenta> obtenerCuentas();

    void listarCuentas();
    Optional<Cuenta> bucarCuentas(int numero);
    void ordenarCuentasxNumero();
}
