package Negocio;

import java.time.LocalDateTime;

public class CuentaDeCheque extends Cuenta {

    private double costoManejoMensual;


    public CuentaDeCheque(Integer numero, LocalDateTime fechaApertura, Double saldo, LocalDateTime fechaCancelacion, double costoManejoMensual) {
        super(numero, fechaApertura, saldo, fechaCancelacion);
        this.costoManejoMensual = costoManejoMensual;
    }

    public double getCostoManejoMensual() {
        return costoManejoMensual;
    }

    public void setCostoManejoMensual(double costoManejoMensual) {
        this.costoManejoMensual = costoManejoMensual;
    }


    @Override
    public double abono(double cantidad) {
        if (cantidad > 0) {
            return this.getSaldo() + cantidad;
        }
        System.out.println("cantidad " + cantidad + " menor al valor minimo");
        return this.getSaldo();
    }

    @Override
    public double retiro(double cantidad) {
        if (this.getSaldo() > 0 && this.getSaldo() >= cantidad) {

            return this.getSaldo() - cantidad;
        } else {
            System.out.println("No pudimos debitar la cantidad " + cantidad);
            return this.getSaldo();
        }
    }

    @Override
    public String toString() {
        return super.toString() + "CuentaDeCheque{" +
                "costoManejoMensual=" + costoManejoMensual +
                '}';
    }
}
