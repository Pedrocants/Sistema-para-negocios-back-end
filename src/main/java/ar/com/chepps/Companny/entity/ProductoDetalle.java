package ar.com.chepps.Companny.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table (name = "ProductoDetalle")
public class ProductoDetalle {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idProductoDetalle;
    private BigDecimal stockActual;
    private BigDecimal stockMinimo;

    public ProductoDetalle() {
    }

    public Long getIdProductoDetalle() {
        return idProductoDetalle;
    }

    public BigDecimal getStockActual() {
        return stockActual;
    }

    public void setStockActual(BigDecimal stockActual) {
        this.stockActual = stockActual;
    }

    public BigDecimal getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(BigDecimal stockMinimo) {
        this.stockMinimo = stockMinimo;
    }
}