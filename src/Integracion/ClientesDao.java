package Integracion;

import Negocio.Cliente;
import Negocio.ServicioCliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClientesDao extends ConectionDao implements ServicioCliente {


    private String query;
    private ResultSet resultSet;
    private ArrayList<Cliente> clientes;


    public ClientesDao(String url, String user, String password) {
        super(url, user, password);
        this.clientes = new ArrayList<>();
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
    public boolean agregarCliente(Cliente cliente) {
        int afectados = 0;
        query = "INSERT INTO CLIENTES(numero,nombre,apellido,edad) VALUES(" + cliente.getCedula() + ",'" + cliente.getNombre() + "','" + cliente.getApellido() + "'," + cliente.getEdad() + ");";
        try {
            afectados = this.getStatement().executeUpdate(query);
            if (afectados == 1) {
                System.out.println("Se insertó el cliente en la DB");
                return true;
            } else {
                System.out.println("No se insertó el cliente en la DB");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Fallo la actualización " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean eliminarCliente(int numero) {
        int afectados = 0;
        query = "DELETE FROM CLIENTES WHERE NUMERO=" + numero;


        try {
            afectados = this.getStatement().executeUpdate(query);
            if (afectados == 1) {
                System.out.println("Se borró el cliente en la DB");
                return true;
            } else {
                System.out.println("No se encontró el cliente en la DB");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Fallo la conexión para el borrado de datos " + e.getMessage());
        }

        return false;
    }

    @Override
    public Optional<Cliente> consultarClientePorNumero(int numero) {

        query = "SELECT * FROM CLIENTES WHERE NUMERO=" + numero;
        try {
            resultSet = this.getStatement().executeQuery(getQuery());
            System.out.println("Datos del Clientes");
            System.out.println("Fecha " + LocalDateTime.now());
            System.out.println("*-".repeat(20));
            if (resultSet.next()) {
                Integer numero1 = resultSet.getInt("numero");
                String apellido = resultSet.getString("apellido");
                String nombre = resultSet.getString("nombre");
                Cliente cliente = new Cliente(nombre, apellido, numero1, 0, null, "", "", LocalDate.now());
                System.out.println(cliente);
                System.out.println("*-".repeat(20));
                return Optional.of(cliente);
            }
            System.out.println("*-".repeat(20));
            getResultSet().close();

        } catch (SQLException e) {
            System.out.println("Fallo listado de clientes " + e.getMessage());
            return Optional.empty();
        }

        return Optional.empty();
    }

    @Override
    public ArrayList<Cliente> obtenerClientes() {
        query = "SELECT * FROM CLIENTES";
        try {
            resultSet = this.getStatement().executeQuery(getQuery());
            while (resultSet.next()) {
                Cliente cliente = new Cliente(resultSet.getString("nombre"), resultSet.getString("apellido"), resultSet.getInt("numero"), resultSet.getInt("edad"), null, "", "", LocalDate.now());
                clientes.add(cliente);
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("Fallo listado de clientes " + e.getMessage());
        }
        return clientes;
    }

    @Override
    public Optional<Cliente> buscarClientePorRfc(String rfc) {
        return Optional.empty();
    }

    @Override
    public void listarClientes() {
        query = "SELECT * FROM CLIENTES";
        try {
            resultSet = this.getStatement().executeQuery(getQuery());
            System.out.println("Reporte de Clientes");
            System.out.println("Fecha " + LocalDateTime.now());
            System.out.println("*-".repeat(20));
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("numero") + " " +
                        resultSet.getString("nombre") + " " +
                        resultSet.getString("apellido"));
            }
            System.out.println("*-".repeat(20));
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("Fallo listado de clientes " + e.getMessage());
        }
    }

    @Override
    public List<Cliente> ordenarClientexNumero() {
        return obtenerClientes().stream().sorted((client1, cliente2) -> client1.getCedula().compareTo(cliente2.getCedula())).collect(Collectors.toList());

    }
}
