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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ClientesDao extends ConectionDao implements ServicioCliente {


    private String query;
    private ResultSet resultSet;
    private ArrayList<Cliente> clientes;
    private static final Logger logger = Logger.getLogger(ClientesDao.class.getName());


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
        ArrayList<Cliente> clienteArrayList = executeSelect(query);
        clienteArrayList.stream().map(client -> {
            logger.info(client.getNombre() + " " + client.getApellido() + " " + client.getCedula());
            return Optional.of(client);
        });
        return Optional.empty();
    }

    @Override
    public ArrayList<Cliente> obtenerClientes() {
        query = "SELECT * FROM CLIENTES";
        return executeSelect(query);
    }

    @Override
    public Optional<Cliente> buscarClientePorRfc(String rfc) {
        return Optional.empty();
    }

    @Override
    public void listarClientes() {
        query = "SELECT * FROM CLIENTES";
        ArrayList<Cliente> clienteArrayList = executeSelect(query);
        if (!clienteArrayList.isEmpty()) {
            logger.info("Reporte de Clientes");
            logger.log(Level.INFO, "Fecha y Hora {0}", LocalDateTime.now());
            clienteArrayList.forEach(cliente -> {
                logger.info(cliente.getNombre() + " " + cliente.getApellido() + " " + cliente.getCedula());
            });
        } else {
            logger.info("Cliente no encontrado");
        }
    }

    @Override
    public List<Cliente> ordenarClientexNumero() {
        return obtenerClientes().stream().sorted((client1, cliente2) -> client1.getCedula().compareTo(cliente2.getCedula())).collect(Collectors.toList());

    }

    public ArrayList<Cliente> executeSelect(String query) {
        try {
            resultSet = this.getStatement().executeQuery(query);
            while (resultSet.next()) {
                clientes.add(new Cliente(resultSet.getString("nombre"), resultSet.getString("apellido"), resultSet.getInt("numero"), resultSet.getInt("edad"), null, "", "", LocalDate.now()));
            }
            resultSet.close();
        } catch (SQLException e) {
            logger.info("Fallo la consulta de clientes " + e.getMessage());
        }
        return clientes;
    }

    public boolean executeOtherQuery(String query) {
        return false;
    }
}
