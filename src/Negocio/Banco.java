package Negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Banco /*implements ServicioCliente*/ {
    private String nombre;
    private Domicilio domicilio;
    private String rfc;
    private String telefono;
    private ArrayList<Cliente> clientes;

    public Banco(String nombre, Domicilio domicilio, String rfc, String telefono) {
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.rfc = rfc;
        this.telefono = telefono;
        this.clientes = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    @Override
    public String toString() {
        return "Banco{" +
                "nombre='" + nombre + '\'' +
                ", domicilio=" + domicilio +
                ", rfc='" + rfc + '\'' +
                ", telefono='" + telefono + '\'' +
                //", clientes=" + clientes +
                '}';
    }

    /*@Override
    public boolean agregarCliente(Cliente cliente) {
        Optional<Cliente> clt = consultarClientePorNumero(cliente.getCedula());
        if (clt.isEmpty()) {
            return clientes.add(cliente);
        }
        return false;
    }

    @Override
    public boolean eliminarCliente(int numero) {

        return consultarClientePorNumero(numero).map(cliente -> clientes.remove(cliente)).orElse(false);
    *//*    if (client.isPresent()) {
            return clientes.remove(client);
        }
        return false;
    *//*
    }

    @Override
    public Optional<Cliente> consultarClientePorNumero(int numero) {

        return clientes.stream().filter(cliente -> cliente.getCedula() == numero).findFirst();
      *//*  for (Cliente cliente : clientes) {
            if (cliente.getCedula() == numero) {
                return cliente;
            }
        }
        return null;
      *//*
    }

    @Override
    public ArrayList<Cliente> obtenerClientes() {
        return clientes;
    }

    @Override
    public Optional<Cliente> buscarClientePorRfc(String rfc) {

        return clientes.stream().filter(cliente -> cliente.getRfc().equals(rfc)).findFirst();
     *//*   for (Cliente cliente : clientes) {
            if (cliente.getRfc() == rfc) {
                return cliente;
            }
        }
        return null;*//*
    }

    @Override
    public void listarClientes() {
        System.out.println("#".repeat(30));
        clientes.forEach(System.out::println);
      *//*  for (Cliente clt : clientes) {
            System.out.println("clt = " + clt);
        }*//*
        System.out.println("#".repeat(30));
    }

    @Override
    public List<Cliente> ordenarClientexNumero() {
       return clientes.stream().sorted((client,client2)-> client.getCedula().compareTo(client2.getCedula())).collect(Collectors.toList());

    }*/
}
