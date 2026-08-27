package ar.com.chepps.Companny.container;

import java.math.BigDecimal;

public class ReportesVentasProductos {

    private Long idProducto;
    private String denominacion;
    private BigDecimal stock;
    private BigDecimal cantidadVendidas;

    public ReportesVentasProductos(Long idProducto, String denominacion, BigDecimal stock,
                                   BigDecimal cantidadVendidas) {
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

    public BigDecimal getStock() {
        return stock;
    }

    public void setStock(BigDecimal stock) {
        this.stock = stock;
    }

    public BigDecimal getCantidadVendidas() {
        return cantidadVendidas;
    }

    public void setCantidadVendidas(BigDecimal cantidadVendidas) {
        this.cantidadVendidas = cantidadVendidas;
    }
}

