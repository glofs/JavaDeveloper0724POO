package Negocio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Cliente extends Persona implements ServicioCuenta {


    private Integer cedula;

    private String telefono;
    private String rfc;


    private ArrayList<Cuenta> cuentas;

    public Cliente(String nombre, String apellido, Integer cedula, int edad, Domicilio domicilio, String telefono, String rfc, LocalDate fechaNacimiento) {
        super(nombre, apellido, edad, domicilio, fechaNacimiento);
        this.cedula = cedula;
        this.telefono = telefono;
        this.rfc = rfc;
        this.cuentas = new ArrayList<>();
    }


    public Integer getCedula() {
        return cedula;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    public String getRfc() {
        LocalDateTime localDateTime;
        return rfc;
    }


    public ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(ArrayList<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    @Override
    public String toString() {
        return super.toString() + "Cliente{" +
                "cedula=" + cedula +
                ", telefono='" + telefono + '\'' +
                ", rfc='" + rfc + '\'' +
                '}';
    }

    @Override
    public boolean agregarCuenta(Cuenta cuenta) {
        Cuenta cta = bucarCuentas(cuenta.getNumero());
        if (cta == null) {
            return cuentas.add(cuenta);
        }
        return false;
    }

    @Override
    public boolean cancelarCuenta(int numero) {
        return false;
    }

    @Override
    public void abonarCuenta(int numero, double abono) {
        Cuenta cuenta = bucarCuentas(numero);
        if (cuenta != null) {
            System.out.println("el nuevo saldo de la cuenta " + cuenta.getNumero() + " es =" + cuenta.abono(abono));
            cuenta.setSaldo(cuenta.abono(abono));
            System.out.println("cuenta.getSaldo() = " + cuenta.getSaldo());
        }
    }

    @Override
    public void retiroCuenta(int numero, double retiro) {
        Cuenta cuenta = bucarCuentas(numero);
        if (cuenta != null) {
            System.out.println("el nuevo saldo de la cuenta " + cuenta.getNumero() + "es =" + cuenta.retiro(retiro));
            cuenta.setSaldo(cuenta.retiro(retiro));
            System.out.println("cuenta.getSaldo() = " + cuenta.getSaldo());
        }
    }

    @Override
    public ArrayList<Cuenta> obtenerCuentas() {
        return cuentas;
    }

    @Override
    public void listarCuentas() {
        for (Cuenta cuenta : cuentas) {
            System.out.println("cuenta = " + cuenta);
        }
    }

    @Override
    public Cuenta bucarCuentas(int numero) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumero() == numero) {
                return cuenta;
            }
        }
        return null;
    }
}
