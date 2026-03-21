package ar.com.chepps.Companny.helpers;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class formateoDecimales {

    public static double formatearDecimal(double numero, int cantidad){
        if (numero == 0 || cantidad == 0) {
            return numero;
        }
        BigDecimal decimal = new BigDecimal(numero);
        double resultado = decimal.setScale(cantidad, RoundingMode.HALF_UP).doubleValue();

        return resultado;
    }
}
