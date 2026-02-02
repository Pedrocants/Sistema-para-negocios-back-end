package ar.com.chepps.Companny.dao;

import ar.com.chepps.Companny.container.ReportesVentasProductos;
import ar.com.chepps.Companny.entity.Orden;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long> {
    @Transactional
    @Query("SELECT SUM(o.total) FROM Orden o " +
            "WHERE o.tipoOrden = 'VENTA'")
    public Double sumaOrdenes();

    @Transactional
    @Query("SELECT SUM(o.total) FROM Orden o " +
            "WHERE o.tipoOrden = 'VENTA' AND o.fecha_carga >= :fecha_carga AND o.estado != " +
            "Estados.cancelada")
    public Double sumaOrdenesPorFecha(@Param("fecha_carga") LocalDateTime fecha_carga);

    @Transactional
    @Query("SELECT SUM(CASE WHEN o.tipoOrden = 'VENTA' THEN o.total ELSE 0 END) - " +
            "SUM(CASE WHEN o.tipoOrden = 'COMPRA' THEN o.total ELSE 0 END) - " +
            "SUM(CASE WHEN o.tipoOrden = 'PAGO' THEN o.total ELSE 0 END) " +
            "FROM Orden o WHERE o.estado != Estados.cancelada AND o.tipoOrden != TipoOrden" +
            ".AGREGACION_DE_STOCK AND tipoOrden != TipoOrden.DEVOLUCION_O_ELIMINACION_DE_STOCK")
    public Double calcularBalance();

    @Query("SELECT SUM(o.pagado) FROM Orden o WHERE o.estado != Estados.cancelada")
    public Double sumaPagosDeOrdenes();

    @Query("SELECT SUM(o.pagado) FROM Orden o WHERE o.fecha_carga >= :fecha_carga AND o.estado !=" +
            " Estados.cancelada AND o.tipoOrden != TipoOrden.DEVOLUCION_O_ELIMINACION_DE_STOCK " +
            "AND o.tipoOrden != AGREGACION_DE_STOCK")
    public Double sumaPagosDeOrdenesPorFecha(@Param("fecha_carga") LocalDateTime fecha_carga);

    @Query("SELECT SUM(CASE WHEN o.tipoOrden = TipoOrden.VENTA THEN o.pagado ELSE 0 END) - SUM" +
            "(CASE WHEN o.tipoOrden = TipoOrden.COMPRA THEN o.pagado ELSE 0 END) - SUM(CASE WHEN " +
            "o.tipoOrden = TipoOrden.PAGO THEN o.pagado ELSE 0 END" +
            ") " +
            "FROM" +
            " " +
            "Orden o" +
            " " +
            "WHERE o" +
            ".fecha_carga " +
            ">= " +
            ":fecha_carga AND o" +
            ".tipoPago" +
            " " +
            "= TipoPago.EFECTIVO AND o.estado != Estados.cancelada")
    public Double sumaPagosEnEfectivoPorFecha(@Param("fecha_carga") LocalDateTime fecha_carga);

    @Transactional
    @Query("SELECT o FROM Orden o WHERE o.fecha_carga >= :fecha_carga AND o.estado != Estados.cancelada")
    Page<Orden> buscarDesdeUnaFecha(@Param("fecha_carga") LocalDateTime fecha_carga,
                                    Pageable pageable);

    @Transactional
    @Query("SELECT o FROM Orden o WHERE o.estado != Estados.cancelada ORDER BY o.idOrden DESC")
    Page<Orden> findAll(Pageable pageable);

    @Transactional
    @Query("SELECT SUM(CASE WHEN o.tipoOrden = 'VENTA' THEN o.total ELSE 0 END) - " +
            "SUM(CASE WHEN o.tipoOrden = 'COMPRA' THEN o.total ELSE 0 END) - " +
            "SUM(CASE WHEN o.tipoOrden = 'PAGO' THEN o.total ELSE 0 END) " +
            "FROM Orden o WHERE o.fecha_carga >= :fecha_carga AND o.estado != Estados.cancelada")
    public Double calcularBalancePorFecha(@Param("fecha_carga") LocalDateTime fecha_carga);

    @Transactional
    @Query("SELECT new ar.com.chepps.Companny.container.ReportesVentasProductos(" +
            "p.idProductoManufacturado, p.denominacion, d.stockActual, SUM(od.cantidadProducto))" +
            " " +
            "FROM ar.com.chepps.Companny.entity.OrdenDetalle od " +
            "JOIN od.orden o " +
            "JOIN od.productos p " +
            "JOIN p.detalle d " +
            "WHERE o.tipoOrden = 'VENTA' " +
            "AND o.fecha_carga BETWEEN :desde AND :hasta " +
            "GROUP BY p.idProductoManufacturado, p.denominacion, d.stockActual")
    List<ReportesVentasProductos> obtenerReporteVentasProductosPorFechas(
            @Param("desde") LocalDateTime desde,
            @Param("hasta") LocalDateTime hasta);

    @Transactional
    @Query("SELECT new ar.com.chepps.Companny.container.ReportesVentasProductos(" +
            "p.idInsumo, p.denominacion, d.stockActual, SUM(od.cantidadInsumo)) " +
            "FROM ar.com.chepps.Companny.entity.OrdenDetalle od " +
            "JOIN od.orden o " +
            "JOIN od.insumo p " +
            "JOIN p.detalle d " +
            "WHERE o.tipoOrden = 'VENTA' " +
            "AND o.fecha_carga BETWEEN :desde AND :hasta " +
            "GROUP BY p.idInsumo, p.denominacion, d.stockActual")
    List<ReportesVentasProductos> obtenerReporteVentasProductos_InsumosPorFechas(
            @Param("desde") LocalDateTime desde,
            @Param("hasta") LocalDateTime hasta);
}
