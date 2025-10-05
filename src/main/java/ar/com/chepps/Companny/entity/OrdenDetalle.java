package ar.com.chepps.Companny.entity;
import jakarta.persistence.*;

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
    private double descuentosPorProducto;
    private String observaciones;
    private double cantidadProducto;
    private double cantidadInsumo;

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

    public double getDescuentosPorProducto() {
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

    public void setDescuentosPorProducto(double descuentosPorProducto) {
        this.descuentosPorProducto = descuentosPorProducto;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public double getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(double cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public double getCantidadInsumo() {
        return cantidadInsumo;
    }

    public void setCantidadInsumo(double cantidadInsumo) {
        this.cantidadInsumo = cantidadInsumo;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }
}