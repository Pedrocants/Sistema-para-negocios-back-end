package ar.com.chepps.Companny.helpers;

import ar.com.chepps.Companny.container.*;
import ar.com.chepps.Companny.entity.*;
import ar.com.chepps.Companny.enums.Estados;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HelperDTO {

    public HelperDTO() {
    }

    public static Object pasarADtoOEntity(Object obj, boolean adto) {
        if (!Objects.isNull(obj)) {
            if (adto) {
                Orden or = (Orden) obj;
                if (or.getEstado() == Estados.cancelada) {
                    return null;
                }

                for (OrdenDetalle d : or.getDetalle()) {
                    d.setOrden(null);
                    if (d.getProductos() != null) {
                        d.getProductos().setHistorial(null);
                    }
                    if (d.getProductos() != null) {
                        if (d.getProductos().getUnidad() != null) {
                            d.getProductos().setUnidad(null);
                        }
                        if (d.getProductos().getInsumos() != null) {
                            d.getProductos().setInsumos(null);
                        }
                        if (d.getProductos().getDetalle() != null) {
                            d.getProductos().setDetalle(null);
                        }
                    }
                    if (d.getInsumo() != null) {
                        if (d.getInsumo().getDetalle() != null) {
                            d.getInsumo().setDetalle(null);
                        }
                        if (d.getInsumo().getProductoManufacturado() != null) {
                            d.getInsumo().setProductoManufacturado(null);
                        }
                        if (d.getInsumo().getUnidadMedida() != null) {
                            d.getInsumo().setUnidadMedida(null);
                        }
                    }
                }
                OrdenDTO dto =
                        new OrdenDTO(or.getIdOrden(), or.getCliente(), or.getContacto(),
                                or.getDomicilio(), or.getUsuario(), or.getDetalle(),
                                or.getFecha_carga(),
                                or.getFecha_entrega(), or.getSubTotal(), or.getTotal(),
                                or.getEstado(),
                                or.getPagado());
                dto.setTipoOrden(or.getTipoOrden());
                dto.setTipoPago(or.getTipoPago());
                return dto;
            } else {
                Orden or = new Orden();
                OrdenDTO dto = (OrdenDTO) obj;
                or.setIdOrden(dto.getIdOrden());
                or.setCliente(dto.getCliente());
                or.setUsuario(dto.getUsuario());
                or.setDetalle(dto.getDetalle());
                or.setContacto(dto.getContacto());
                or.setDomicilio(dto.getDomicilio());
                or.setSubTotal(dto.getSubTotal());
                or.setTotal(dto.getTotal());
                or.setFecha_carga(dto.getFecha_carga());
                or.setFecha_entrega(dto.getFecha_entrega());
                or.setEstado(dto.getEstado());
                or.setPagado(dto.getPagado());
                or.setTipoOrden(dto.getTipoOrden());
                or.setTipoPago(dto.getTipoPago());
                return or;
            }
        }
        return new Object();
    }

    public static OrdenDetalleDTO retornarDetalle(Orden orden) {
        if (!Objects.isNull(orden)) {
            OrdenDetalleDTO dtodetalle = new OrdenDetalleDTO(
                    orden.getIdOrden(),
                    (orden.getCliente() != null) ?
                            orden.getCliente().getNombre() + " " + orden.getCliente().getApellido() : "Cliente local (caja)",
                    orden.getContacto(), orden.getDomicilio(), orden.getFecha_carga(),
                    orden.getFecha_entrega(), orden.getSubTotal(), orden.getTotal(),
                    orden.getEstado(), orden.getPagado()
            );
            List<OrdenDetalle> detalles = orden.getDetalle();
            List<ProductoDTODetalle> productosParaMostrar = new ArrayList<>();
            ProductoDTODetalle dtoProductoDetalle;
            for (OrdenDetalle det : detalles) {
                if (!Objects.isNull(det.getProductos())) {

                    dtoProductoDetalle = (!Objects.isNull(det.getProductos())) ?
                            new ProductoDTODetalle(
                                    det.getProductos().getDenominacion(),
                                    det.getProductos().getTiempo_estimado(),
                                    det.getProductos().getPrecio()
                            ) : null;
                    dtoProductoDetalle.setCantidadProducto(det.getCantidadProducto());
                    productosParaMostrar.add(dtoProductoDetalle);
                }
                if (!Objects.isNull(det.getInsumo())) {
                    dtoProductoDetalle = new ProductoDTODetalle(
                            det.getInsumo().getDenominacion(),
                            null,
                            det.getInsumo().getPrecio()
                    );
                    dtoProductoDetalle.setCantidadProducto(det.getCantidadInsumo());
                    if (det.getInsumo().getCosto() != null) {
                        dtoProductoDetalle.setCosto(det.getInsumo().getCosto());
                    }
                    productosParaMostrar.add(dtoProductoDetalle);
                }
            }
            dtodetalle.setProductos(productosParaMostrar);
            return dtodetalle;
        }
        return null;
    }

    public static Object convertirADtoOEntity(Object obj, boolean aDto) {
        if (aDto) {
            Insumo i = (Insumo) obj;
            InsumoDTO dto = new InsumoDTO(i.getIdInsumo(), i.getUnidadMedida(), i.getDetalle(),
                    i.getPrecio(), i.getDenominacion());
            dto.setEliminado(i.getEliminado());
            dto.setEsParaElaborar(i.getEsParaElaborar());
            if (i.getMarca() != null) {
                MarcaDTO mdto = new MarcaDTO(
                        i.getMarca().getIdMarca(),
                        i.getMarca().getNombre(),
                        new ArrayList<>()
                );
                dto.setMarca(mdto);
            }
            if (i.getCosto() != null) {
                dto.setCosto(i.getCosto());
            }
            return dto;

        }
        InsumoDTO dto = (InsumoDTO) obj;
        Marca marca = new Marca();
        Insumo i = new Insumo();
        i.setIdInsumo(dto.getIdInsumo());
        i.setUnidadMedida(dto.getUnidadMedida());
        i.setPrecio(dto.getPrecio());
        i.setDenominacion(dto.getDenominacion());
        i.setDetalle(dto.getDetalle());
        i.setEliminado(dto.getEliminado());
        i.setEsParaElaborar(dto.getEsParaElaborar());
        if (dto.getMarca() != null) {
            marca.setIdMarca((dto.getMarca().getIdMarca() != null) ? dto.getMarca().getIdMarca()
                    : null);
            marca.setNombre((dto.getMarca().getNombre() != null && !dto.getMarca().getNombre().isBlank()) ? dto.getMarca().getNombre() : null);
            marca.setInsumos((dto.getMarca().getInsumos() != null) ? dto.getMarca().getInsumos() :
                    null);
            i.setMarca(marca);
        }
        if (dto.getCosto() != null) {
            i.setCosto(dto.getCosto());
        }
        return i;
    }

    public static Object convertirMarca(Object o, boolean aDto) throws NullPointerException {
        if (Objects.isNull(o)) {
            return new NullPointerException("Objeto null");
        }
        if (aDto) {
            Marca marca = (Marca) o;
            MarcaDTO marcaDTO = new MarcaDTO();

            marcaDTO.setIdMarca(marca.getIdMarca());
            marcaDTO.setNombre(marca.getNombre());
            return marcaDTO;
        }
        MarcaDTO dto = (MarcaDTO) o;
        Marca marca = new Marca();

        marca.setIdMarca(dto.getIdMarca());
        marca.setNombre(dto.getNombre());
        marca.setInsumos((dto.getInsumos() != null) ? dto.getInsumos() : null);

        return marca;
    }

    public static <T> void actualizarCamposDiferentes(T target, T source) {
        try {
            for (Field field : target.getClass().getDeclaredFields()) {
                field.setAccessible(true);

                Object valorActual = field.get(target);
                Object valorNuevo = field.get(source);

                if (!Objects.equals(valorActual, valorNuevo) && valorNuevo != null && valorActual != null) {
                    field.set(target, valorNuevo);
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Error actualizando campos", e);
        }
    }

    public static Object productoADTO(Object p, boolean adto) {

        if (adto) {
            ProductoManufacturado mnf = (ProductoManufacturado) p;
            ProductoDTO dto;
            ArrayList<InsumoDTO> insumosDTO = new ArrayList<>();
            InsumoDTO insumoDTO;
            dto = new ProductoDTO();

            dto.setIdProductoManufacturado(mnf.getIdProductoManufacturado());
            dto.setDenominacion(mnf.getDenominacion());
            dto.setUnidad(mnf.getUnidad());
            dto.setCantVendidas(mnf.getCantVendidas());
            dto.setDescripcion(mnf.getDescripcion());
            dto.setDetalle(mnf.getDetalle());
            dto.setEliminado(mnf.isEliminado());
            dto.setPrecio(mnf.getPrecio());
            dto.setTiempo_estimado(mnf.getTiempo_estimado());
            for (Insumo i : mnf.getInsumos()) {
                insumoDTO = new InsumoDTO();
                insumoDTO.setDenominacion(i.getDenominacion());
                insumoDTO.setPrecio(i.getPrecio());
                insumoDTO.setDetalle(i.getDetalle());
                insumoDTO.setIdInsumo(i.getIdInsumo());
                insumoDTO.setUnidadMedida(i.getUnidadMedida());
                if (i.getMarca() != null) {
                    insumoDTO.setMarca(new MarcaDTO(i.getMarca().getIdMarca(),
                            i.getMarca().getNombre(), null));
                }
                if (i.getCosto() != null) {
                    insumoDTO.setCosto(i.getCosto());
                }
                insumosDTO.add(insumoDTO);
            }
            dto.setInsumos(insumosDTO);
            return dto;

        } else {
            ProductoManufacturado mnf = new ProductoManufacturado();
            ProductoDTO dto = (ProductoDTO) p;
            Insumo insumo;
            List<Insumo> insumos = new ArrayList<>();
            mnf.setIdProductoManufacturado(dto.getIdProductoManufacturado());
            mnf.setDenominacion(dto.getDenominacion());
            mnf.setUnidad(dto.getUnidad());
            mnf.setCantVendidas(dto.getCantVendidas());
            mnf.setDescripcion(dto.getDescripcion());
            mnf.setDetalle(dto.getDetalle());
            mnf.setEliminado(dto.isEliminado());
            mnf.setPrecio(dto.getPrecio());
            mnf.setTiempo_estimado(dto.getTiempo_estimado());
            for (InsumoDTO idto : dto.getInsumos()) {
                insumo = new Insumo();
                insumo.setDenominacion(idto.getDenominacion());
                insumo.setPrecio(idto.getPrecio());
                insumo.setDetalle(idto.getDetalle());
                insumo.setIdInsumo(idto.getIdInsumo());
                insumo.setUnidadMedida(idto.getUnidadMedida());
                if (idto.getCosto() != null) {
                    insumo.setCosto(idto.getCosto());
                }
                insumos.add(insumo);
            }
            mnf.setInsumos(insumos);
            mnf.setHistorial((dto.getHistorial() != null) ? dto.getHistorial() : null);
            return mnf;
        }
    }

    public static ArrayList<HistorialProductoDTO> getHistorial(ProductoManufacturado p) {
        ArrayList<HistorialProductoDTO> historial = new ArrayList<>();
        HistorialProductoDTO phd;
        if (p != null && p.getHistorial() != null) {
            for (Historial h : p.getHistorial()) {
                phd = new HistorialProductoDTO();
                phd.setNombreProducto(h.getProducto().getDenominacion());
                phd.setNombreInsumo(h.getInsumo().getDenominacion());
                phd.setCantidadInsumo(h.getCantidad());
                historial.add(phd);
            }
        }
        return historial;
    }
}
