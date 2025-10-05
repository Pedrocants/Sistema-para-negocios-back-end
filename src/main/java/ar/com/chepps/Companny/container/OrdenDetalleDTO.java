package ar.com.chepps.Companny.container;

import ar.com.chepps.Companny.entity.*;
import ar.com.chepps.Companny.enums.Estados;
import java.time.LocalDateTime;
import java.util.List;

public class OrdenDetalleDTO {
    //DTO de orden para mostrar
    private Long idOrden;
    private String cliente;
    private Contacto contacto;
    private Domicilio domicilio;
    private LocalDateTime fecha_carga;
    private LocalDateTime fecha_entrega;
    private double subTotal;
    private double total;
    private Estados estado = Estados.parcial_pendiente;
    private double pagado;
    private List<ProductoDTODetalle> productos;

    public OrdenDetalleDTO() {
    }

    public OrdenDetalleDTO(Long idOrden, String cliente, Contacto contacto, Domicilio domicilio, LocalDateTime fecha_carga, LocalDateTime fecha_entrega, double subTotal, double total, Estados estado, double pagado) {
        this.idOrden = idOrden;
        this.cliente = cliente;
        this.contacto = contacto;
        this.domicilio = domicilio;
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

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
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

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Estados getEstado() {
        return estado;
    }

    public void setEstado(Estados estado) {
        this.estado = estado;
    }

    public double getPagado() {
        return pagado;
    }

    public void setPagado(double pagado) {
        this.pagado = pagado;
    }

    public List<ProductoDTODetalle> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoDTODetalle> productos) {
        this.productos = productos;
    }
}
