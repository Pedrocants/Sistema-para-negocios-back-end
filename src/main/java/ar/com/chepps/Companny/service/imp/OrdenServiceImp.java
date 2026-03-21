package ar.com.chepps.Companny.service.imp;

import ar.com.chepps.Companny.container.*;
import ar.com.chepps.Companny.dao.*;
import ar.com.chepps.Companny.entity.*;
import ar.com.chepps.Companny.enums.Estados;
import ar.com.chepps.Companny.enums.TipoOrden;
import ar.com.chepps.Companny.helpers.HelperDTO;
import ar.com.chepps.Companny.helpers.formateoDecimales;
import ar.com.chepps.Companny.service.IOrdenService;
import ar.com.chepps.Companny.service.OrdenClienteProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class OrdenServiceImp implements IOrdenService {
    @Autowired
    private OrdenRepository repo;
    @Autowired
    private ProductoManufacturadoRepository prdRepo;
    @Autowired
    private InsumoRepository insumoRepo;
    @Autowired
    private ClienteRepository clienteRepo;
    @Autowired
    private ContactoRepository repoCon;
    @Autowired
    private DomicilioRepository repoDom;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public OrdenDetalleDTO agregarOrden(OrdenDTO or, boolean update) throws RuntimeException {
        if (or == null) {
            return new OrdenDetalleDTO();
        }

        or.setEstado(or.getPagado() < or.getTotal() ? Estados.parcial_pendiente : Estados.pagada);
        Orden o = (Orden) HelperDTO.pasarADtoOEntity(or, false);

        List<OrdenDetalle> detallesActualizados = o.getDetalle().stream()
                .peek(d -> d.setOrden(o))
                .collect(Collectors.toList());

        o.setDetalle(detallesActualizados);
        if (o.getCliente() != null) {
            Cliente cliente = (o.getCliente().getIdCliente() == null) ?
                    clienteRepo.save(o.getCliente()) : o.getCliente();
            o.setCliente(cliente);
        }
        if (o.getContacto() != null) {
            Contacto contacto = (o.getContacto().getIdContacto() == null) ?
                    repoCon.save(o.getContacto()) :
                    repoCon.findById(o.getContacto().getIdContacto()).orElse(null);
            o.setContacto(contacto);
        }
        if (o.getDomicilio() != null) {
            Domicilio d = (o.getDomicilio().getIdDomicilio() == null) ?
                    repoDom.save(o.getDomicilio()) :
                    repoDom.findById(o.getDomicilio().getIdDomicilio()).orElse(null);
            o.setDomicilio(d);
        }
        Orden ordenGuardada = repo.save(o);
        if (!update) {
            if (o.getTipoOrden() == TipoOrden.VENTA || o.getTipoOrden() == TipoOrden.DEVOLUCION_O_ELIMINACION_DE_STOCK) {
                definirOrden(o, false);
            }
            if (o.getTipoOrden() == TipoOrden.COMPRA || o.getTipoOrden() == TipoOrden.AGREGACION_DE_STOCK) {
                definirOrden(o, true);
            }
        }
        return HelperDTO.retornarDetalle(ordenGuardada);
    }

    @Transactional
    @Override
    public PaginacionDTO<OrdenDetalleDTO> mostrarOrdenes(LocalDateTime fecha_carga,
                                                         int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (fecha_carga == null) {
            Page<OrdenDetalleDTO> ordenesPage = repo.findAllResumen(pageable);
            List<OrdenDetalleDTO> ordenesDTO = ordenesPage.getContent();
            return new PaginacionDTO<>(
                    ordenesDTO,
                    ordenesPage.getNumber(),
                    ordenesPage.getTotalPages(),
                    ordenesPage.getTotalElements(),
                    ordenesPage.getSize()
            );
        }
        Page<Orden> ordenesPage = repo.buscarDesdeUnaFecha(fecha_carga, pageable);

        List<OrdenDetalleDTO> ordenesDTO = ordenesPage
                .getContent()
                .stream()
                .map(HelperDTO::retornarDetalle)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return new PaginacionDTO<>(
                ordenesDTO,
                ordenesPage.getNumber(),
                ordenesPage.getTotalPages(),
                ordenesPage.getTotalElements(),
                ordenesPage.getSize()
        );
    }

    public OrdenDTO buscarPorId(Long idOrden) {
        if (idOrden != null) {
            OrdenDTO dto = repo.findById(idOrden)
                    .map(o -> (OrdenDTO) HelperDTO.pasarADtoOEntity(o, true))
                    .orElse(null);
            return dto;
        }
        return new OrdenDTO();
    }

    @Override
    public String eliminarOrden(Long idOrden) {
        Optional or = repo.findById(idOrden);
        if (or.isPresent()) {
            Orden o = (Orden) or.get();
            o.setEstado(Estados.cancelada);
            repo.save(o);
            return "Orden cancelada";
        }

        return "no encontrada";
    }

    private void definirOrden(Orden o, boolean esCompra) {
        ProductoManufacturado prd;
        ProductoDetalle prdDetalle;
        for (OrdenDetalle d : o.getDetalle()) {
            prd = (d.getProductos() != null) ?
                    prdRepo.findById(d.getProductos().getIdProductoManufacturado())
                            .orElse(null) : null;
            prdDetalle = (!Objects.isNull(prd)) ? prd.getDetalle() : null;

            if (!Objects.isNull(prd)) {


                if (prdDetalle.getStockActual() >= d.getCantidadProducto() && !esCompra) {
                    prdDetalle.setStockActual(formateoDecimales.formatearDecimal(prdDetalle.getStockActual() - d.getCantidadProducto(), 3));
                    prd.setDetalle(prdDetalle);

                    prdRepo.save(prd);
                } else if (esCompra && d.getCantidadProducto() > 0) {
                    prdDetalle.setStockActual(formateoDecimales.formatearDecimal(prdDetalle.getStockActual() + d.getCantidadProducto(), 3));
                    prd.setDetalle(prdDetalle);
                    if (!prd.getInsumos().isEmpty()) {
                        modificarStockInsumos(prd, d.getCantidadProducto());
                    }

                    prdRepo.save(prd);
                } else {
                    throw new IllegalArgumentException("La cantidad es mayor al stock actual! Stock: " + prdDetalle.getStockActual() + " cantidad: " + d.getCantidadProducto());
                }
            }
            if (d.getInsumo() != null) {
                Insumo i = insumoRepo.findById(d.getInsumo().getIdInsumo()).orElse(null);
                if (!Objects.isNull(i) && i.getDetalle().getStockActual() >= d.getCantidadInsumo() && !esCompra) {
                    ProductoDetalle insumoD = i.getDetalle();
                    insumoD.setStockActual(formateoDecimales.formatearDecimal(insumoD.getStockActual() - d.getCantidadInsumo(), 3));
                    i.setDetalle(insumoD);
                    insumoRepo.save(i);
                    i = null;
                    insumoD = null;
                } else if (esCompra && i.getDetalle().getStockActual() >= d.getCantidadInsumo() || esCompra && i.getDetalle().getStockActual() <= d.getCantidadInsumo()) {
                    ProductoDetalle insumoD = i.getDetalle();
                    insumoD.setStockActual(formateoDecimales.formatearDecimal(insumoD.getStockActual() + d.getCantidadInsumo(),
                            3));
                    i.setDetalle(insumoD);
                    insumoRepo.save(i);
                    i = null;
                    insumoD = null;
                }
            }
            prd = null;
            prdDetalle = null;
        }
    }

    private void modificarStockInsumos(ProductoManufacturado p, double cantidad) {
        ProductoDetalle d;
        double cantInsumo = 0;
        for (Insumo i : p.getInsumos()) {
            if (!Objects.isNull(i)) {
                for (Historial historial : p.getHistorial()) {
                    if (historial.getInsumo().equals(i) && historial.getInsumo().getIdInsumo() == i.getIdInsumo()) {
                        cantInsumo = (historial.getCantidad() * cantidad);
                        d = i.getDetalle();
                        d.setStockActual(formateoDecimales.formatearDecimal(d.getStockActual() - cantInsumo, 3));
                        i.setDetalle(d);
                        insumoRepo.save(i);
                    }
                }
            }
        }
    }

    public SumaOrdenesDTO sumarOrdenes() throws NullPointerException {
        Double suma = repo.sumaOrdenes();
        Double balance = repo.calcularBalance();
        Double ordenesPagas = repo.sumaPagosDeOrdenes();
        if (suma != null) {
            SumaOrdenesDTO sumaDTO = new SumaOrdenesDTO();
            sumaDTO.setTotal(suma);
            sumaDTO.setBalance((balance != null) ? balance : 0);
            sumaDTO.setPagado((ordenesPagas != null) ? ordenesPagas : 0);
            return sumaDTO;
        }
        return new SumaOrdenesDTO();
    }

    public SumaOrdenesDTO sumarOrdenesPorFecha(LocalDateTime fecha_carga) throws NullPointerException {
        Double suma = repo.sumaOrdenesPorFecha(fecha_carga);
        Double balance = repo.calcularBalancePorFecha(fecha_carga);
        Double ordenesPagas = repo.sumaPagosDeOrdenesPorFecha(fecha_carga);
        Double pagosEfectivo = repo.sumaPagosEnEfectivoPorFecha(fecha_carga);
        if (suma != null) {
            SumaOrdenesDTO sumaDTO = new SumaOrdenesDTO();
            sumaDTO.setTotal(suma);
            sumaDTO.setBalance((balance != null) ? balance : 0);
            sumaDTO.setPagado((ordenesPagas != null) ? ordenesPagas : 0);
            sumaDTO.setEfectivo(pagosEfectivo != null ? pagosEfectivo : 0);
            return sumaDTO;
        }
        return new SumaOrdenesDTO();
    }

    public List<ReportesVentasProductos> getReporteVentas(LocalDateTime desde,
                                                          LocalDateTime hasta) {
        List<ReportesVentasProductos> p = repo.obtenerReporteVentasProductosPorFechas(desde, hasta);
        List<ReportesVentasProductos> i =
                repo.obtenerReporteVentasProductos_InsumosPorFechas(desde, hasta);
        List<ReportesVentasProductos> lista = Stream.concat(p.stream(), i.stream()).toList();

        return lista;
    }
    @Override
    public OrdenClienteProjection obtenerDatosPorCliente(Long idCliente) {
        return repo.findDatosCliente(idCliente, PageRequest.of(0, 1))
                .stream()
                .findFirst()
                .orElse(null);
    }
}
