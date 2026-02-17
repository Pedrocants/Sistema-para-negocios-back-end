package ar.com.chepps.Companny.service;

import ar.com.chepps.Companny.container.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface IOrdenService {
    public OrdenDetalleDTO agregarOrden(OrdenDTO or, boolean update);

    public PaginacionDTO<OrdenDetalleDTO> mostrarOrdenes(LocalDateTime fecha_carga, int page,
                                                         int size);

    public OrdenDTO buscarPorId(Long idOrden);

    public String eliminarOrden(Long idOrden);

    public SumaOrdenesDTO sumarOrdenes() throws NullPointerException;

    public SumaOrdenesDTO sumarOrdenesPorFecha(LocalDateTime fecha_carga) throws NullPointerException;

    public List<ReportesVentasProductos> getReporteVentas(LocalDateTime desde, LocalDateTime hasta);
    OrdenClienteProjection obtenerDatosPorCliente(Long idCliente);
}
