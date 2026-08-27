package ar.com.chepps.Companny.container;

import ar.com.chepps.Companny.entity.Historial;
import ar.com.chepps.Companny.entity.ProductoDetalle;
import ar.com.chepps.Companny.entity.UnidadMedida;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ProductoDTO {
    private Long idProductoManufacturado;
    private UnidadMedida unidad;
    private ProductoDetalle detalle;
    private List<InsumoDTO> insumos;
    private List<Historial> historial;
    private String denominacion;
    private BigDecimal precio = BigDecimal.ZERO;
    private int cantVendidas;
    private String descripcion;
    private LocalDateTime tiempo_estimado;
    private boolean eliminado;

    public ProductoDTO() {
    }

    public ProductoDTO(Long idProductoManufacturado, UnidadMedida unidad, ProductoDetalle detalle, List<InsumoDTO> insumos, String denominacion, BigDecimal precio, int cantVendidas, String descripcion, LocalDateTime tiempo_estimado, boolean eliminado) {
        this.idProductoManufacturado = idProductoManufacturado;
        this.unidad = unidad;
        this.detalle = detalle;
        this.insumos = insumos;
        this.denominacion = denominacion;
        this.precio = precio;
        this.cantVendidas = cantVendidas;
        this.descripcion = descripcion;
        this.tiempo_estimado = tiempo_estimado;
        this.eliminado = eliminado;
    }

    public Long getIdProductoManufacturado() {
        return idProductoManufacturado;
    }

    public void setIdProductoManufacturado(Long idProductoManufacturado) {
        this.idProductoManufacturado = idProductoManufacturado;
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

    public List<InsumoDTO> getInsumos() {
        return insumos;
    }

    public void setInsumos(List<InsumoDTO> insumos) {
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

    public List<Historial> getHistorial() {
        return historial;
    }

    public void setHistorial(List<Historial> historial) {
        this.historial = historial;
    }
}
