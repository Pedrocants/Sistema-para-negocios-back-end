package ar.com.chepps.Companny.container;

public class HistorialProductoDTO {
    private String nombreProducto;
    private String nombreInsumo;
    private double cantidadInsumo;

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

    public double getCantidadInsumo() {
        return cantidadInsumo;
    }

    public void setCantidadInsumo(double cantidadInsumo) {
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
