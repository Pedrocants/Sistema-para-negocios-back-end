package ar.com.chepps.Companny.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Insumo")
public class Insumo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInsumo;
    @ManyToOne
    @JoinColumn(name = "idUnidadMedida",
            referencedColumnName = "idUnidadMedida")
    private UnidadMedida unidadMedida;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idProductoDetalle")
    private ProductoDetalle detalle;
    @ManyToMany(mappedBy = "insumos")
    private List<ProductoManufacturado> productoManufacturado;
    private double precio = 0;
    private String denominacion;
    private Boolean esParaElaborar;
    private Boolean eliminado;
    private BigDecimal costo = BigDecimal.valueOf(0);

    @ManyToOne
    @JoinColumn(name = "idMarca",
            referencedColumnName = "idMarca")
    @JsonIgnoreProperties("insumos")
    private Marca marca;

    public Insumo() {
    }

    public Long getIdInsumo() {
        return idInsumo;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public ProductoDetalle getDetalle() {
        return detalle;
    }

    public double getPrecio() {
        return precio;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public Boolean getEsParaElaborar() {
        return esParaElaborar;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public void setDetalle(ProductoDetalle detalle) {
        this.detalle = detalle;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public void setEsParaElaborar(Boolean esParaElaborar) {
        this.esParaElaborar = esParaElaborar;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    public List<ProductoManufacturado> getProductoManufacturado() {
        return productoManufacturado;
    }

    public void setProductoManufacturado(List<ProductoManufacturado> productoManufacturado) {
        this.productoManufacturado = productoManufacturado;
    }

    public void setIdInsumo(Long idInsumo) {
        this.idInsumo = idInsumo;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "Insumo{" +
                "idInsumo=" + idInsumo +
                ", unidadMedida=" + unidadMedida +
                ", detalle=" + detalle +
                ", productoManufacturado=" + productoManufacturado +
                ", precio=" + precio +
                ", denominacion='" + denominacion + '\'' +
                ", esParaElaborar=" + esParaElaborar +
                ", eliminado=" + eliminado +
                ", marca=" + marca.toString() +
                '}';
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }
}