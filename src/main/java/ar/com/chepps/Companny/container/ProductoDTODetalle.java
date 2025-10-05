package ar.com.chepps.Companny.container;

import java.time.LocalDateTime;

public class ProductoDTODetalle {
    //Solo nombre de productos para mostrar
    private String nombre;
    private LocalDateTime tiempo_estimado;
    private Double precio;
    private double cantidadProducto;

    public ProductoDTODetalle(String nombre, LocalDateTime tiempo_estimado, Double precio) {
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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public double getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(double cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }
}
