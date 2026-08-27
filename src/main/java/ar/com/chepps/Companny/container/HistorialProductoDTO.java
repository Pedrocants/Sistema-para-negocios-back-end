package ar.com.chepps.Companny.container;

import java.math.BigDecimal;

public class HistorialProductoDTO {
    private String nombreProducto;
    private String nombreInsumo;
    private BigDecimal cantidadInsumo;

    public HistorialProductoDTO() {
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getNombreInsumo() {
        return nombreInsumo;
    }

    public void setNombreInsumo(String nombreInsumo) {
        this.nombreInsumo = nombreInsumo;
    }

    public BigDecimal getCantidadInsumo() {
        return cantidadInsumo;
    }

    public void setCantidadInsumo(BigDecimal cantidadInsumo) {
        this.cantidadInsumo = cantidadInsumo;
    }

    @Override
    public String toString() {
        return "Detalles: " +
                "nombre de Producto='" + nombreProducto + '\'' +
                ", nombre de Insumo='" + nombreInsumo + '\'' +
                ", cantidad de Insumo='" + cantidadInsumo;
    }
}
