package ar.com.chepps.Companny.controller;

import ar.com.chepps.Companny.container.OrdenDetalleDTO;
import ar.com.chepps.Companny.container.SumaOrdenesDTO;
import ar.com.chepps.Companny.service.IExcelReportService;
import ar.com.chepps.Companny.service.IOrdenService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReportesController {
    @Autowired
    private IOrdenService srv;
    @Autowired
    private IExcelReportService excelReportService;

    @GetMapping("/reporte-ventas")
    public void reporteVentas(HttpServletResponse response,
                              @RequestParam("desde") @DateTimeFormat(iso =
                                      DateTimeFormat.ISO.DATE_TIME) LocalDateTime desde,
                              @RequestParam("hasta") @DateTimeFormat(iso =
                                      DateTimeFormat.ISO.DATE_TIME) LocalDateTime hasta) throws IOException {
        excelReportService.exportarExcelCantidadVendidas(response, srv.getReporteVentas(desde,
                hasta));
    }

    @GetMapping("/excel")
    @ResponseStatus(HttpStatus.OK)
    public void generarReporteExcel(HttpServletResponse response, @RequestParam(defaultValue = "0"
    ) int page, @RequestParam(defaultValue = "1000000") int size) throws IOException {
        List<OrdenDetalleDTO> ordenes = srv.mostrarOrdenes(null, page, size).getContent();
        SumaOrdenesDTO calculos = srv.sumarOrdenes();
        excelReportService.exportarExcel(response, ordenes, calculos);
    }

    @GetMapping("/excelPorFecha")
    @ResponseStatus(HttpStatus.OK)
    public void generarReporteExcelPorFecha(HttpServletResponse response, @RequestParam(
            "fecha_carga") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fecha_carga, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10000") int size) throws IOException {
        List<OrdenDetalleDTO> ordenes = srv.mostrarOrdenes(fecha_carga, page, size).getContent();
        SumaOrdenesDTO calculos = srv.sumarOrdenesPorFecha(fecha_carga);
        excelReportService.exportarExcel(response, ordenes, calculos);
    }
}
