package ar.com.chepps.Companny.entity;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table (name = "OrdenDetalle")
public class OrdenDetalle {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idOrdenDetalle;
    @ManyToOne
    @JoinColumn(name = "idOrden",
            referencedColumnName = "idOrden")
    private Orden orden;
    @ManyToOne
    @JoinColumn(name = "idProductoManufacturado",
    referencedColumnName = "idProductoManufacturado")
    private ProductoManufacturado productos;
    @ManyToOne
    @JoinColumn (name = "idProductoInsumo",
    referencedColumnName = "idInsumo")
    private Insumo insumo;
    private BigDecimal descuentosPorProducto = BigDecimal.ZERO;
    private String observaciones;
    private BigDecimal cantidadProducto;
    private BigDecimal precioInsumo;
    private BigDecimal precioProducto;
    private BigDecimal cantidadInsumo;

    public OrdenDetalle() {
    }

    public Long getIdOrdenDetalle() {
        return idOrdenDetalle;
    }

    public ProductoManufacturado getProductos() {
        return productos;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public BigDecimal getDescuentosPorProducto() {
        return descuentosPorProducto;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setProductos(ProductoManufacturado productos) {
        this.productos = productos;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public void setDescuentosPorProducto(BigDecimal descuentosPorProducto) {
        this.descuentosPorProducto = descuentosPorProducto;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public BigDecimal getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(BigDecimal cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public BigDecimal getCantidadInsumo() {
        return cantidadInsumo;
    }

    public void setCantidadInsumo(BigDecimal cantidadInsumo) {
        this.cantidadInsumo = cantidadInsumo;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public BigDecimal getPrecioInsumo() {
        return precioInsumo;
    }

    public void setPrecioInsumo(BigDecimal precioInsumo) {
        this.precioInsumo = precioInsumo;
    }

    public BigDecimal getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(BigDecimal precioProducto) {
        this.precioProducto = precioProducto;
    }
}