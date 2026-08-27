package ar.com.chepps.Companny.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ProductoManufacturado")
public class ProductoManufacturado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProductoManufacturado;
    @ManyToOne
    @JoinColumn (name = "idUnidadMedida",
            referencedColumnName = "idUnidadMedida")
    private UnidadMedida unidad;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "idProductoDetalle",
            referencedColumnName = "idProductoDetalle")
    private ProductoDetalle detalle;
    @ManyToMany
    @JoinTable(
            name = "ProductoManufacturado_Insumo",
            joinColumns = @JoinColumn(name = "idProductoManufacturado"),
            inverseJoinColumns = @JoinColumn(name = "idInsumo")
    )
    private List<Insumo> insumos;
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<Historial> historial;
    private String denominacion;
    private BigDecimal precio = BigDecimal.ZERO;
    private int cantVendidas;
    private String descripcion;
    private LocalDateTime tiempo_estimado;
    private boolean eliminado;

    public ProductoManufacturado() {
    }

    public Long getIdProductoManufacturado() {
        return idProductoManufacturado;
    }

    public UnidadMedida getUnidad() {
        return unidad;
    }

    public void setUnidad(UnidadMedida unidad) {
        this.unidad = unidad;
    }

    public ProductoDetalle getDetalle() {
        return detalle;
    }

    public void setDetalle(ProductoDetalle detalle) {
        this.detalle = detalle;
    }

    public List<Insumo> getInsumos() {
        return insumos;
    }

    public void setInsumos(List<Insumo> insumos) {
        this.insumos = insumos;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public int getCantVendidas() {
        return cantVendidas;
    }

    public void setCantVendidas(int cantVendidas) {
        this.cantVendidas = cantVendidas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getTiempo_estimado() {
        return tiempo_estimado;
    }

    public void setTiempo_estimado(LocalDateTime tiempo_estimado) {
        this.tiempo_estimado = tiempo_estimado;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public void setIdProductoManufacturado(Long idProductoManufacturado) {
        this.idProductoManufacturado = idProductoManufacturado;
    }

    public List<Historial> getHistorial() {
        return historial;
    }

    public void setHistorial(List<Historial> historial) {
        this.historial = historial;
    }
}