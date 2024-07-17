package Negocio;

import java.util.ArrayList;

public interface ServicioCliente {

    boolean agregarCliente(Cliente cliente);

    boolean eliminarCliente(int numero);

    Cliente consultarCliente(int numero);

    ArrayList<Cliente> obtenerClientes();

    Cliente buscarClientePorRfc(String rfc);

    void listarClientes ();
}
