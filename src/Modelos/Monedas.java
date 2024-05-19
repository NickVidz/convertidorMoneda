package Modelos;

import java.util.ArrayList;

public class Monedas {
    //Moneda de la cual se va a hacer la conversion
    public String monedaBase;
    //Moneda a la cual se hara la conversion
    public String monedaAConvertir;
    //Cantidad a ser convertida
    public double cantidadBase;
    //Cantidad convertida
    public String cantidadConvertida;
    //Valor de la moneda actual
    public double tasaDeCambioActual;

    public static ArrayList<Monedas> historical = new ArrayList<>();


    public void setCantidadBase(double cantidadBase) {
        this.cantidadBase = cantidadBase;
    }

    public Monedas(ChangeApiRecord changedApi) {
        String formmatedNumber = String.format("%.2f", changedApi.conversion_result());
        this.monedaBase = changedApi.base_code();
        this.monedaAConvertir = changedApi.target_code();
        this.cantidadConvertida = formmatedNumber;
        this.tasaDeCambioActual = changedApi.conversion_rate();
    }


    @Override
    public String toString() {
        return  "Cantidad ingresada: $" + cantidadBase +
                " De: '" + monedaBase + '\'' +
                " a: '" + monedaAConvertir + '\'' +
                " resultado: $" + cantidadConvertida;
    }
}
