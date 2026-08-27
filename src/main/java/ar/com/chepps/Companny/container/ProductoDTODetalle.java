package ar.com.chepps.Companny.container;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductoDTODetalle {
    //Solo nombre de productos para mostrar
    private String nombre;
    private LocalDateTime tiempo_estimado;
    private BigDecimal precio;
    private BigDecimal cantidadProducto;
    private BigDecimal costo;

    public ProductoDTODetalle(String nombre, LocalDateTime tiempo_estimado, BigDecimal precio) {
        this.nombre = nombre;
        this.tiempo_estimado = tiempo_estimado;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDateTime getTiempo_estimado() {
        return tiempo_estimado;
    }

    public void setTiempo_estimado(LocalDateTime tiempo_estimado) {
        this.tiempo_estimado = tiempo_estimado;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(BigDecimal cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }
}