package ar.com.chepps.Companny.container;

public class ReportesVentasProductos {

    private Long idOrden;
    private String denominacion;
    private Double cantidadVendidas;

    public ReportesVentasProductos(Long idOrden, String denominacion, Double cantidadVendidas) {
        this.idOrden = idOrden;
        this.denominacion = denominacion;
        this.cantidadVendidas = cantidadVendidas;
    }

    public Long getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Long idOrden) {
        this.idOrden = idOrden;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public Double getCantidadVendidas() {
        return cantidadVendidas;
    }

    public void setCantidadVendidas(Double cantidadVendidas) {
        this.cantidadVendidas = cantidadVendidas;
    }
}

