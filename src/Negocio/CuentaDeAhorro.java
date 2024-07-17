package Negocio;

import java.time.LocalDateTime;

public class CuentaDeAhorro extends Cuenta {

    private double tasaDeInteresMensual;

    public CuentaDeAhorro(Integer numero, LocalDateTime fechaApertura, Double saldo, LocalDateTime fechaCancelacion, double tasaDeInteresMensual) {
        super(numero, fechaApertura, saldo, fechaCancelacion);
        this.tasaDeInteresMensual = tasaDeInteresMensual;
    }

    public double getTasaDeInteresMensual() {
        return tasaDeInteresMensual;
    }

    public void setTasaDeInteresMensual(double tasaDeInteresMensual) {
        this.tasaDeInteresMensual = tasaDeInteresMensual;
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
        if (this.getSaldo()>0 && this.getSaldo()>=cantidad){

            return this.getSaldo() - cantidad;
        }else {
            System.out.println("No pudimos debitar la cantidad "+cantidad);
            return this.getSaldo();
        }
    }

    @Override
    public String toString() {
        return super.toString()+ "CuentaDeAhorro{" +
                "tasaDeInteresMensual=" + tasaDeInteresMensual +
                '}';
    }

    public double calcularIntereses() {
        return 1d;
    }
}
