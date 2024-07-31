package Negocio;

import java.time.LocalDateTime;

//funcionan como anotaciones para recordar que cada clase que herede debe implementarla
//las interfaces son clases abstractas puras
public abstract class Cuenta {

    private Integer numero;
    private LocalDateTime fechaApertura;
    private Double saldo;
    private LocalDateTime fechaCancelacion;

    public Cuenta(Integer numero, LocalDateTime fechaApertura, Double saldo, LocalDateTime fechaCancelacion) {
        this.numero = numero;
        this.fechaApertura = fechaApertura;
        this.saldo = saldo;
        this.fechaCancelacion = fechaCancelacion;
    }

    public LocalDateTime getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(LocalDateTime fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public LocalDateTime getFechaCancelacion() {
        return fechaCancelacion;
    }

    public void setFechaCancelacion(LocalDateTime fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public abstract double abono(double cantidad);


    public abstract double retiro(double cantidad);


    @Override
    public String toString() {
        return "Cuenta{" +
                "numero=" + numero +
                ", fechaApertura='" + fechaApertura + '\'' +
                ", saldo=" + saldo +
                ", fechaCancelacion='" + fechaCancelacion + '\'' +
                '}';
    }
}
