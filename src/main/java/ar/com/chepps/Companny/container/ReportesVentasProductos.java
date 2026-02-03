package ar.com.chepps.Companny.container;

public class ReportesVentasProductos {

    private Long idProducto;
    private String denominacion;
    private Double stock;
    private Double cantidadVendidas;

    public ReportesVentasProductos(Long idProducto, String denominacion, Double stock,
                                   Double cantidadVendidas) {
        this.idProducto = idProducto;
        this.denominacion = denominacion;
        this.stock = stock;
        this.cantidadVendidas = cantidadVendidas;
    }

    public Long getIdOrden() {
        return idProducto;
    }

    public void setIdOrden(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }

    public Double getCantidadVendidas() {
        return cantidadVendidas;
    }

    public void setCantidadVendidas(Double cantidadVendidas) {
        this.cantidadVendidas = cantidadVendidas;
    }
}

