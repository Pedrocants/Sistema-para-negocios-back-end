package ar.com.chepps.Companny.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table (name = "Historial")
public class Historial {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idHistorial;
    @ManyToOne
    @JoinColumn (name = "idProductoManufacturado",
    referencedColumnName = "idProductoManufacturado")
    private ProductoManufacturado producto;
    @ManyToOne
    @JoinColumn (name = "idInsumo",
    referencedColumnName = "idInsumo")
    private Insumo insumo;
    private BigDecimal cantidad;
    private String denominacion;

    public Historial() {
    }

    public Long getIdHistorial() {
        return idHistorial;
    }

    public ProductoManufacturado getProducto() {
        return producto;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setProducto(ProductoManufacturado producto) {
        this.producto = producto;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }
}
