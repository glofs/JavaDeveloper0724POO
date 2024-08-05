package Integracion;

import Negocio.Cuenta;
import Negocio.CuentaDeAhorro;
import Negocio.ServicioCuenta;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CuentasDao extends ConectionDao implements ServicioCuenta {
    private String query;
    private ResultSet resultSet;
    private ArrayList<Cuenta> cuentas;

    public CuentasDao(String url, String user, String password) {
        super(url, user, password);
        this.cuentas = new ArrayList<>();
    }


    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }


    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }


    @Override
    public boolean agregarCuenta(Cuenta cuenta) {
        int afectados = 0;
        DateTimeFormatter timestampw = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        query = "INSERT INTO CUENTAS(numero,saldo,fecha_apertura) VALUES(" + cuenta.getNumero() + "," + cuenta.getSaldo() + ",'" + cuenta.getFechaApertura().format(timestampw) + "');";
        System.out.println("query = " + query);
        try {
            afectados = this.getStatement().executeUpdate(query);
            if (afectados == 1) {
                System.out.println("Se insertó la  cuenta en la DB");
                return true;
            } else {
                System.out.println("No se insertó la cuenta en la DB");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Fallo en agregar el cliente " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean cancelarCuenta(int numero) {

        int afectados = 0;
        query = "DELETE FROM CUENTAS WHERE NUMERO=" + numero;
        try {
            afectados = this.getStatement().executeUpdate(query);
            if (afectados == 1) {
                System.out.println("Se borró la  cuenta en la DB");
                return true;
            } else {
                System.out.println("No se encontró la cuenta en la DB");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Fallo la conexión para el borrado de cuenta " + e.getMessage());
        }

        return false;
    }


    @Override
    public void abonarCuenta(int numero, double abono) {

    }

    @Override
    public void retiroCuenta(int numero, double abono) {

    }

    @Override
    public ArrayList<Cuenta> obtenerCuentas() {

        query = "SELECT * FROM CUENTAS";
        try {
            resultSet = this.getStatement().executeQuery(getQuery());
            while (resultSet.next()) {
                Cuenta cuenta = new CuentaDeAhorro(resultSet.getInt("numero"), resultSet.getTimestamp("fecha_apertura").toLocalDateTime(), resultSet.getDouble("saldo"), null, 1);
                cuentas.add(cuenta);
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("Fallo listado de cuentas " + e.getMessage());
        }
        return cuentas;
    }

    @Override
    public void listarCuentas() {
        query = "SELECT * FROM CUENTAS";
        try {
            resultSet = this.getStatement().executeQuery(getQuery());
            System.out.println("Datos de la Cuentas");
            System.out.println("Fecha " + LocalDateTime.now());
            System.out.println("*-".repeat(20));
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("numero") + " " +
                        resultSet.getInt("saldo") + " " +
                        resultSet.getTimestamp("fecha_apertura") + " " +
                        resultSet.getTimestamp("fecha_cancelacion"));

            }
            System.out.println("*-".repeat(20));
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("Fallo listado de cuentas " + e.getMessage());
        }

    }

    @Override
    public Optional<Cuenta> bucarCuentas(int numero) {
        return Optional.empty();
    }

    @Override
    public List<Cuenta> ordenarCuentasxNumero() {
        return obtenerCuentas().stream().sorted((cuenta1, cuenta2) -> cuenta1.getNumero().compareTo(cuenta2.getNumero())).collect(Collectors.toList());

    }
}
