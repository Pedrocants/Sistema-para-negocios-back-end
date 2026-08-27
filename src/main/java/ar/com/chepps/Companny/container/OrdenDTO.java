package ar.com.chepps.Companny.container;

import ar.com.chepps.Companny.entity.*;
import ar.com.chepps.Companny.enums.Estados;
import ar.com.chepps.Companny.enums.TipoOrden;
import ar.com.chepps.Companny.enums.TipoPago;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrdenDTO {
    private Long idOrden;
    private Cliente cliente;
    private Contacto contacto;
    private Domicilio domicilio;
    private Usuario usuario;
    private List<OrdenDetalle> detalle;
    private LocalDateTime fecha_carga = LocalDateTime.now();
    private LocalDateTime fecha_entrega;
    private BigDecimal subTotal = BigDecimal.ZERO;
    private BigDecimal total = BigDecimal.ZERO;
    private Estados estado = Estados.parcial_pendiente;
    private BigDecimal pagado = BigDecimal.ZERO;
    private TipoOrden tipoOrden = TipoOrden.VENTA;
    private TipoPago tipoPago = TipoPago.EFECTIVO;

    public OrdenDTO() {
    }

    public OrdenDTO(Long idOrden, Cliente cliente, Contacto contacto, Domicilio domicilio, Usuario usuario, List<OrdenDetalle> detalle, LocalDateTime fecha_carga, LocalDateTime fecha_entrega, BigDecimal subTotal, BigDecimal total, Estados estado, BigDecimal pagado) {
        this.idOrden = idOrden;
        this.cliente = cliente;
        this.contacto = contacto;
        this.domicilio = domicilio;
        this.usuario = usuario;
        this.detalle = detalle;
        this.fecha_carga = fecha_carga;
        this.fecha_entrega = fecha_entrega;
        this.subTotal = subTotal;
        this.total = total;
        this.estado = estado;
        this.pagado = pagado;
    }

    public Long getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Long idOrden) {
        this.idOrden = idOrden;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<OrdenDetalle> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<OrdenDetalle> detalle) {
        this.detalle = detalle;
    }

    public LocalDateTime getFecha_carga() {
        return fecha_carga;
    }

    public void setFecha_carga(LocalDateTime fecha_carga) {
        this.fecha_carga = fecha_carga;
    }

    public LocalDateTime getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(LocalDateTime fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
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
