package ar.com.chepps.Companny.container;

import ar.com.chepps.Companny.entity.ProductoDetalle;
import ar.com.chepps.Companny.entity.UnidadMedida;

import java.math.BigDecimal;

public class InsumoDTO {
    private Long idInsumo;
    private UnidadMedida unidadMedida;
    private ProductoDetalle detalle;
    private double precio = 0;
    private String denominacion;
    private Boolean esParaElaborar;
    private Boolean eliminado;
    private MarcaDTO marca;
    private BigDecimal costo;

    public InsumoDTO() {
    }

    public InsumoDTO(Long idInsumo, UnidadMedida unidadMedida, ProductoDetalle detalle,
                     double precio, String denominacion) {
        this.idInsumo = idInsumo;
        this.unidadMedida = unidadMedida;
        this.detalle = detalle;
        this.precio = precio;
        this.denominacion = denominacion;
    }

    public Long getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(Long idInsumo) {
        this.idInsumo = idInsumo;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public ProductoDetalle getDetalle() {
        return detalle;
    }

    public void setDetalle(ProductoDetalle detalle) {
        this.detalle = detalle;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    public Boolean getEsParaElaborar() {
        return esParaElaborar;
    }

    public void setEsParaElaborar(Boolean esParaElaborar) {
        this.esParaElaborar = esParaElaborar;
    }

    public MarcaDTO getMarca() {
        return marca;
    }

    public void setMarca(MarcaDTO marca) {
        this.marca = marca;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    @Override
    public String toString() {
        return "InsumoDTO{" +
                "idInsumo=" + idInsumo +
                ", unidadMedida=" + unidadMedida +
                ", detalle=" + detalle +
                ", precio=" + precio +
                ", denominacion='" + denominacion + '\'' +
                ", esParaElaborar=" + esParaElaborar +
                ", eliminado=" + eliminado +
                ", marca=" + marca.getNombre() +
                '}';
    }
}