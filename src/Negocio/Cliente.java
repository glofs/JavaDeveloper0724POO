package Negocio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

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
        Optional<Cuenta> cta = bucarCuentas(cuenta.getNumero());
        if (cta.isEmpty()) {
            return cuentas.add(cuenta);
        }
        return false;
    }

    @Override
    public boolean cancelarCuenta(int numero) {
        return bucarCuentas(numero).map(cuenta -> cuentas.remove(cuenta)).isPresent();
    }

    @Override
    public void abonarCuenta(int numero, double abono) {
        bucarCuentas(numero).map(cuenta -> {
            cuenta.setSaldo(cuenta.abono(abono));
            System.out.println("el nuevo saldo de la cuenta " + cuenta.getNumero() + "es = " + cuenta.getSaldo());
            return Optional.empty();
        });


    }

    @Override
    public void retiroCuenta(int numero, double retiro) {
        bucarCuentas(numero).map(cta -> {
            cta.setSaldo(cta.retiro(retiro));
            System.out.println("el nuevo saldo de la cuenta " + cta.getNumero() + "es = " + cta.getSaldo());
            return Optional.empty();
        });


    }


    @Override
    public ArrayList<Cuenta> obtenerCuentas() {
        return cuentas;
    }

    @Override
    public void listarCuentas() {
        System.out.println("#".repeat(30));
        cuentas.forEach(System.out::println);
        System.out.println("#".repeat(30));
    /*    for (Cuenta cuenta : cuentas) {
            System.out.println("cuenta = " + cuenta);
        }*/
    }

    @Override
    public Optional<Cuenta> bucarCuentas(int numero) {

        return cuentas.stream().filter(cta -> cta.getNumero() == numero).findFirst();

        /*for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumero() == numero) {
                return cuenta;
            }
        }
        return null;*/
    }

    @Override
    public void ordenarCuentasxNumero() {
        System.out.println("#".repeat(30));
        cuentas.stream().sorted((cuenta1, cuenta2) -> cuenta1.getNumero().compareTo(cuenta2.getNumero())).toList().forEach(System.out::println);
        System.out.println("#".repeat(30));
    }
}
