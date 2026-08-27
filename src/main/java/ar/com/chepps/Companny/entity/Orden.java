package ar.com.chepps.Companny.entity;

import ar.com.chepps.Companny.enums.Estados;
import ar.com.chepps.Companny.enums.TipoPago;
import ar.com.chepps.Companny.enums.TipoOrden;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table (name = "Orden")
public class Orden {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idOrden;
    @ManyToOne
    @JoinColumn (name = "idCliente",
    referencedColumnName = "idCliente")
    private Cliente cliente;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "idContacto",
    referencedColumnName = "idContacto")
    private Contacto contacto;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "idDomicilio",
    referencedColumnName = "idDomicilio")
    private Domicilio domicilio;
    @OneToOne
    @JoinColumn (name = "idUsuario",
    referencedColumnName = "idUsuario")
    private Usuario usuario;
    @OneToMany (mappedBy = "orden", cascade = CascadeType.ALL)
    private List<OrdenDetalle> detalle;
    private LocalDateTime fecha_carga = LocalDateTime.now();
    private LocalDateTime fecha_entrega;
    private BigDecimal subTotal = BigDecimal.ZERO;
    private BigDecimal total = BigDecimal.ZERO;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estados estado = Estados.parcial_pendiente;
    private BigDecimal pagado = BigDecimal.ZERO;
    @Enumerated(EnumType.STRING)
    private TipoOrden tipoOrden = TipoOrden.VENTA;
    @Enumerated(EnumType.STRING)
    private TipoPago tipoPago = TipoPago.EFECTIVO;


    public Orden() {
    }

    public Long getIdOrden() {
        return idOrden;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDateTime getFecha_carga() {
        return fecha_carga;
    }

    public LocalDateTime getFecha_entrega() {
        return fecha_entrega;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setFecha_carga(LocalDateTime fecha_carga) {
        this.fecha_carga = fecha_carga;
    }

    public void setFecha_entrega(LocalDateTime fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<OrdenDetalle> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<OrdenDetalle> detalle) {
        this.detalle = detalle;
    }

    public Estados getEstado() {
        return estado;
    }

    public void setEstado(Estados estado) {
        this.estado = estado;
    }

    public BigDecimal getPagado() {
        return pagado;
    }

    public void setPagado(BigDecimal pagado) {
        this.pagado = pagado;
    }

    /*public Double totalOrden(boolean subtotal){
        ArrayList<OrdenDetalle> detalles = (ArrayList) this.getDetalle();
        Double totalProductos = 0.0;
        for(OrdenDetalle d : detalles){
            for(int i = 0; i < d.getProductos().size(); i++){
                totalProductos += (Double) (d.getProductos().get(i).getPrecio()) + (d.getInsumo().get(i).getPrecio());
                //Si no es subtotal, restar si hay descuentos.
                if(!subtotal && (Double)d.getDescuentosPorProducto() != null){
                    totalProductos -= (Double) (d.getDescuentosPorProducto());
                }
            }
        }
        return totalProductos;
    }*/

    public void setIdOrden(Long idOrden) {
        this.idOrden = idOrden;
    }

    public TipoOrden getTipoOrden() {
        return tipoOrden;
    }

    public void setTipoOrden(TipoOrden tipoOrden) {
        this.tipoOrden = tipoOrden;
    }

    public TipoPago getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(TipoPago tipoPago) {
        this.tipoPago = tipoPago;
    }
}
