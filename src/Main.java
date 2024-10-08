import Integracion.ClientesDao;
import Integracion.CuentasDao;
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

        Cliente cliente2 = new Cliente("Maria Angelica", "Bueno", 1234, 45, domicilio, "12345", "123", LocalDate.of(1988, 11, 11));
        Cliente cliente3 = new Cliente("Alberto", "Bueno", 1233, 45, domicilio, "12345", "123", LocalDate.now().minusMonths(1));
//
//        Banco banco = new Banco("Bancolombia", domicilio, "123456", "(710-4148989)");
//        System.out.println("banco = " + banco);
//        banco.agregarCliente(cliente);
//        banco.agregarCliente(cliente2);
//        banco.agregarCliente(cliente3);
//        banco.listarClientes();
//        banco.eliminarCliente(1234567);
//        banco.listarClientes();
//
        CuentaDeAhorro cuentaDeAhorro = new CuentaDeAhorro(27400118, LocalDateTime.now().minusYears(2), 1000.0, null, 0.5);
        CuentaDeAhorro cuentaDeAhorro2 = new CuentaDeAhorro(777901211, LocalDateTime.now().minusMonths(2), 1000.0, null, 0.5);
        CuentaDeCheque cuentaDeAhorro3 = new CuentaDeCheque(274001102, LocalDateTime.now().minusMonths(2), 1000.0, null, 0.5);
//
//        cliente.agregarCuenta(cuentaDeAhorro);
//        cliente.agregarCuenta(cuentaDeAhorro2);
//        cliente.agregarCuenta(cuentaDeAhorro3);
//        cliente.listarCuentas();
//        cliente.abonarCuenta(274001100, 1000.0);
//        cliente.abonarCuenta(274001102, 5000.0);
//        cliente.cancelarCuenta(777901211);
//        cliente.retiroCuenta(274001100,500);
//        cliente.listarCuentas();
//        banco.ordenarClientexNumero().forEach(System.out::println);
//        cliente.ordenarCuentasxNumero();


        String url = "jdbc:postgresql://localhost:5432/banca";
        String user = "postgres";
        String password = "admin";


        ClientesDao clientesDao = new ClientesDao(url,user,password);
        clientesDao.listarClientes();
        clientesDao.consultarClientePorNumero(3425);
        clientesDao.eliminarCliente(1234);
        clientesDao.agregarCliente(cliente2);
        clientesDao.listarClientes();
        clientesDao.ordenarClientexNumero().forEach(System.out::println);

        CuentasDao cuentasDao=new CuentasDao(url,user,password);
        cuentasDao.listarCuentas();
        cuentasDao.cancelarCuenta(27400118);
        cuentasDao.agregarCuenta(cuentaDeAhorro);
        System.out.println("*-".repeat(20));
        cuentasDao.ordenarCuentasxNumero().forEach(System.out::println);
        System.out.println("*-".repeat(20));


    }
}