package Negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ServicioCliente {

    boolean agregarCliente(Cliente cliente);

    boolean eliminarCliente(int numero);

    Optional<Cliente> consultarClientePorNumero(int numero);

    ArrayList<Cliente> obtenerClientes();

    Optional<Cliente> buscarClientePorRfc(String rfc);

    void listarClientes ();
    List<Cliente> ordenarClientexNumero();
}
