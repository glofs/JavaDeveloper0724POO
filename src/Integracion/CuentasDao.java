package Integracion;

import Negocio.Cuenta;
import Negocio.CuentaDeAhorro;
import Negocio.ServicioCuenta;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CuentasDao implements ServicioCuenta {
    private String url;
    private String user;
    private String password;
    private String query;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private ArrayList<Cuenta> cuentas;


    public CuentasDao(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.cuentas = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(getUrl(), getUser(), getPassword());
            statement = connection.createStatement();
        } catch (
                SQLException e) {
            System.out.println("FALLO CONEXIÓN DB " + e.getMessage());
        }

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
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
            afectados = statement.executeUpdate(query);
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
            afectados = statement.executeUpdate(query);
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
            resultSet = statement.executeQuery(getQuery());
            while (resultSet.next()) {
                Cuenta cuenta = new CuentaDeAhorro(resultSet.getInt("numero"), resultSet.getTimestamp("fecha_apertura").toLocalDateTime(), resultSet.getDouble("saldo"),null,1);
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
            resultSet = statement.executeQuery(getQuery());
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
        return;

    }

    @Override
    public Optional<Cuenta> bucarCuentas(int numero) {
        return Optional.empty();
    }

    @Override
    public List<Cuenta> ordenarCuentasxNumero() {
       return obtenerCuentas().stream().sorted((cuenta1,cuenta2)->cuenta1.getNumero().compareTo(cuenta2.getNumero())).collect(Collectors.toList());

    }
}
