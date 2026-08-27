package ar.com.chepps.Companny.helpers;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class formateoDecimales {

    public static BigDecimal formatearDecimal(BigDecimal numero, int cantidad){
        if (numero == null || numero.compareTo(BigDecimal.ZERO) == 0 || cantidad == 0) {
            return numero;
        }
        return numero.setScale(cantidad, RoundingMode.HALF_UP);
    }

    public static double formatearDecimal(double numero, int cantidad){
        if (numero == 0 || cantidad == 0) {
            return numero;
        }
        BigDecimal decimal = BigDecimal.valueOf(numero);
        return decimal.setScale(cantidad, RoundingMode.HALF_UP).doubleValue();
    }
}
