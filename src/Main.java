import Negocio.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Domicilio domicilio = new Domicilio("65", 45, "2", "DC", 90);
        Cliente cliente = new Cliente("Gustavo", "Bueno", 123113, 45, domicilio, "12345", "123", LocalDate.of(1991, 12, 1));
        System.out.println("cliente = " + cliente);


        Persona persona2 = new Persona("Gustavo", "Lozada", 33, null, LocalDate.now().minusYears(1));
        Persona persona1 = new Persona("Gustavo", "Lozada", 33, null, LocalDate.now().minusYears(1));
        System.out.println("persona1.hashCode() = " + persona1.hashCode());
        System.out.println("persona2.hashCode() = " + persona2.hashCode());

        Cliente cliente2 = new Cliente("Maria Angelica", "Bueno", 123, 45, domicilio, "12345", "123", LocalDate.of(1988, 11, 11));
        Cliente cliente3 = new Cliente("Alberto", "Bueno", 1233, 45, domicilio, "12345", "123", LocalDate.now().minusMonths(1));

        Banco banco = new Banco("Bancolombia", domicilio, "123456", "(710-4148989)");
        System.out.println("banco = " + banco);
        banco.agregarCliente(cliente);
        banco.agregarCliente(cliente2);
        banco.agregarCliente(cliente3);
        banco.listarClientes();

        CuentaDeAhorro cuentaDeAhorro = new CuentaDeAhorro(274001100, LocalDateTime.now().minusHours(2), 1000d, null, 0.5);
        CuentaDeAhorro cuentaDeAhorro2 = new CuentaDeAhorro(777901211, LocalDateTime.now().minusHours(1), 1000d, null, 0.5);
        CuentaDeCheque cuentaDeAhorro3 = new CuentaDeCheque(274001102, LocalDateTime.now().minusMinutes(30), 1000d, null, 0.5);

        cliente.agregarCuenta(cuentaDeAhorro);
        cliente.agregarCuenta(cuentaDeAhorro2);
        cliente.agregarCuenta(cuentaDeAhorro3);
        cliente.listarCuentas();
        cliente.abonarCuenta(274001100, 1000.0);
        cliente.abonarCuenta(274001102, 5000.0);
        cliente.listarCuentas();

    }
}