package ar.com.chepps.Companny.entity;

import jakarta.persistence.*;

@Entity
@Table (name = "ProductoDetalle")
public class ProductoDetalle {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idProductoDetalle;
    private double stockActual;
    private double stockMinimo;

    public ProductoDetalle() {
    }

    public Long getIdProductoDetalle() {
        return idProductoDetalle;
    }

    public double getStockActual() {
        return stockActual;
    }

    public void setStockActual(double stockActual) {
        this.stockActual = stockActual;
    }

    public double getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(double stockMinimo) {
        this.stockMinimo = stockMinimo;
    }
}