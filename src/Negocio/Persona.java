package Negocio;

import java.time.LocalDate;
import java.util.Objects;

public class Persona {

    private String nombre;
    private String apellido;
    private int edad;
    private Domicilio domicilio;
    private LocalDate fechaNacimiento;

    public Persona(String nombre, String apellido, int edad, Domicilio domicilio, LocalDate fechaNacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.domicilio = domicilio;
        this.fechaNacimiento = fechaNacimiento;
    }


    public Persona(String nombre, String calle, Integer numero, String colonia, String estado, Integer codigoPostal, String apellido, int edad, LocalDate fechaNacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.fechaNacimiento = fechaNacimiento;
        Domicilio elDomicilio = new Domicilio(calle, numero, colonia, estado, codigoPostal);
        this.setDomicilio(elDomicilio);
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", domicilio=" + domicilio +
                ", fechaNacimiento=" + fechaNacimiento +
                '}';
    }

    //para hacer ahorro de memoria y no duplicar objetos
    @Override
    public int hashCode() {
        return Objects.hash(nombre, apellido, edad, domicilio);
    }
}
