package ar.com.chepps.Companny.service;

import ar.com.chepps.Companny.container.OrdenDetalleDTO;
import ar.com.chepps.Companny.container.ReportesVentasProductos;
import ar.com.chepps.Companny.container.SumaOrdenesDTO;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface IExcelReportService {
    public void exportarExcel(HttpServletResponse response, List<OrdenDetalleDTO> ordenes,
                              SumaOrdenesDTO calculos) throws IOException;

    public void exportarExcelCantidadVendidas(HttpServletResponse response,
                                              List<ReportesVentasProductos> reportes) throws IOException;
}
