package ar.com.chepps.Companny.service.imp;

import ar.com.chepps.Companny.container.OrdenDetalleDTO;
import ar.com.chepps.Companny.container.ReportesVentasProductos;
import ar.com.chepps.Companny.container.SumaOrdenesDTO;
import ar.com.chepps.Companny.service.IExcelReportService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ExcelReportService implements IExcelReportService {


    public void exportarExcel(HttpServletResponse response, List<OrdenDetalleDTO> ordenes,
                              SumaOrdenesDTO calculos) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Reporte de Órdenes");

        XSSFCellStyle headerStyle = workbook.createCellStyle();
        XSSFFont headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 12);
        headerStyle.setFont(headerFont);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setFillForegroundColor(new XSSFColor(new byte[]{(byte) 100, (byte) 100, (byte) 100}, null));
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);

        XSSFCellStyle dataStyle = workbook.createCellStyle();
        dataStyle.setFillForegroundColor(new XSSFColor(new byte[]{(byte) 200, (byte) 200, (byte) 200}, null));
        dataStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        dataStyle.setBorderBottom(BorderStyle.THIN);
        dataStyle.setBorderTop(BorderStyle.THIN);
        dataStyle.setBorderLeft(BorderStyle.THIN);
        dataStyle.setBorderRight(BorderStyle.THIN);

        XSSFCellStyle statusStyle = workbook.createCellStyle();
        XSSFFont statusFont = workbook.createFont();
        statusFont.setColor(new XSSFColor(new byte[]{(byte) 0, (byte) 112, (byte) 192}, null));
        statusStyle.setFont(statusFont);
        statusStyle.setFillForegroundColor(new XSSFColor(new byte[]{(byte) 200, (byte) 200, (byte) 200}, null));
        statusStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        statusStyle.setBorderBottom(BorderStyle.THIN);
        statusStyle.setBorderTop(BorderStyle.THIN);
        statusStyle.setBorderLeft(BorderStyle.THIN);
        statusStyle.setBorderRight(BorderStyle.THIN);

        XSSFCellStyle numberStyle = workbook.createCellStyle();
        numberStyle.setDataFormat(workbook.createDataFormat().getFormat("#,##0.00")); // Formato de moneda/decimal
        numberStyle.setFillForegroundColor(new XSSFColor(new byte[]{(byte) 200, (byte) 200, (byte) 200}, null));
        numberStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        numberStyle.setBorderBottom(BorderStyle.THIN);
        numberStyle.setBorderTop(BorderStyle.THIN);
        numberStyle.setBorderLeft(BorderStyle.THIN);
        numberStyle.setBorderRight(BorderStyle.THIN);

        XSSFCellStyle dateStyle = workbook.createCellStyle();
        dateStyle.setDataFormat(workbook.createDataFormat().getFormat("dd/mm/yyyy hh:mm"));
        dateStyle.setFillForegroundColor(new XSSFColor(new byte[]{(byte) 200, (byte) 200, (byte) 200}, null));
        dateStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        dateStyle.setBorderBottom(BorderStyle.THIN);
        dateStyle.setBorderTop(BorderStyle.THIN);
        dateStyle.setBorderLeft(BorderStyle.THIN);
        dateStyle.setBorderRight(BorderStyle.THIN);

        Row headerRow = sheet.createRow(0);
        String[] encabezados = {"ID ORDEN", "Estado", "Total", "Fecha de Carga"};

        for (int i = 0; i < encabezados.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(encabezados[i]);
            cell.setCellStyle(headerStyle);
        }
        int rowNum = 1;
        for (OrdenDetalleDTO orden : ordenes) {
            Row row = sheet.createRow(rowNum++);

            Cell idOrdenCell = row.createCell(0);
            idOrdenCell.setCellValue(orden.getIdOrden());
            idOrdenCell.setCellStyle(dataStyle);

            Cell estadoCell = row.createCell(1);
            estadoCell.setCellValue(orden.getEstado().toString());
            estadoCell.setCellStyle(statusStyle);

            Cell totalCell = row.createCell(2);
            totalCell.setCellValue(orden.getTotal());
            totalCell.setCellStyle(numberStyle);

            Cell fechaCargaCell = row.createCell(3);
            fechaCargaCell.setCellValue(orden.getFecha_carga());
            fechaCargaCell.setCellStyle(dateStyle);
        }
        rowNum++;

        Row totalVentasRow = sheet.createRow(rowNum++);
        Cell totalVentasLabelCell = totalVentasRow.createCell(0);
        totalVentasLabelCell.setCellValue("TOTAL VENTAS: ");
        totalVentasLabelCell.setCellStyle(dataStyle); // Aplica estilo de datos

        Cell totalVentasValueCell = totalVentasRow.createCell(3);
        totalVentasValueCell.setCellValue(calculos.getTotal());
        totalVentasValueCell.setCellStyle(numberStyle);

        Row totalBalanceRow = sheet.createRow(rowNum++);
        Cell totalBalanceLabelCell = totalBalanceRow.createCell(0);
        totalBalanceLabelCell.setCellValue("TOTAL BALANCE: ");
        totalBalanceLabelCell.setCellStyle(dataStyle);

        Cell totalBalanceValueCell = totalBalanceRow.createCell(3);
        totalBalanceValueCell.setCellValue(calculos.getBalance());
        totalBalanceValueCell.setCellStyle(numberStyle);

        Row totalPagosRow = sheet.createRow(rowNum++);
        Cell totalPagosLabelCell = totalPagosRow.createCell(0);
        totalPagosLabelCell.setCellValue("TOTAL ORDENES PAGAS: ");
        totalPagosLabelCell.setCellStyle(dataStyle);

        Cell totalPagosValueCell = totalPagosRow.createCell(3);
        totalPagosValueCell.setCellValue(calculos.getPagado());
        totalPagosValueCell.setCellStyle(numberStyle);

        Row totalEfectivo = sheet.createRow(rowNum++);
        Cell totalPagosEfectivoLabel = totalEfectivo.createCell(0);
        totalPagosEfectivoLabel.setCellValue("EFECTIVO: ");
        totalPagosEfectivoLabel.setCellStyle(dataStyle);

        Cell totalPagosEfectivoVal = totalEfectivo.createCell(3);
        totalPagosEfectivoVal.setCellValue(calculos.getEfectivo());
        totalPagosEfectivoVal.setCellStyle(numberStyle);

        for (int i = 0; i < encabezados.length; i++) {
            sheet.autoSizeColumn(i);
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=reporte_ordenes.xlsx";
        response.setHeader(headerKey, headerValue);

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

    @Override
    public void exportarExcelCantidadVendidas(HttpServletResponse response,
                                              List<ReportesVentasProductos> reportes) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Reporte de Ventas");

        XSSFCellStyle headerStyle = workbook.createCellStyle();
        XSSFFont headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 12);
        headerStyle.setFont(headerFont);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setFillForegroundColor(new XSSFColor(new byte[]{(byte) 100, (byte) 100, (byte) 100}, null)); // Gris oscuro (RGB)
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);

        XSSFCellStyle productNameStyle = workbook.createCellStyle();
        XSSFFont productNameFont = workbook.createFont();
        productNameFont.setColor(new XSSFColor(new byte[]{(byte) 0, (byte) 112, (byte) 192}, null)); // Azul oscuro (RGB)
        productNameStyle.setFont(productNameFont);
        productNameStyle.setBorderBottom(BorderStyle.THIN);
        productNameStyle.setBorderTop(BorderStyle.THIN);
        productNameStyle.setBorderLeft(BorderStyle.THIN);
        productNameStyle.setBorderRight(BorderStyle.THIN);
        productNameStyle.setFillForegroundColor(new XSSFColor(new byte[]{(byte) 200, (byte) 200, (byte) 200}, null)); // Gris claro (RGB)
        productNameStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle numberStyle = workbook.createCellStyle();
        numberStyle.setDataFormat(workbook.createDataFormat().getFormat("#,##0"));
        numberStyle.setBorderBottom(BorderStyle.THIN);
        numberStyle.setBorderTop(BorderStyle.THIN);
        numberStyle.setBorderLeft(BorderStyle.THIN);
        numberStyle.setBorderRight(BorderStyle.THIN);
        numberStyle.setFillForegroundColor(new XSSFColor(new byte[]{(byte) 200, (byte) 200, (byte) 200}, null)); // Gris claro (RGB)
        numberStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle dataStyle = workbook.createCellStyle();
        dataStyle.setFillForegroundColor(new XSSFColor(new byte[]{(byte) 200, (byte) 200, (byte) 200}, null)); // Gris claro (RGB)
        dataStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        dataStyle.setBorderBottom(BorderStyle.THIN);
        dataStyle.setBorderTop(BorderStyle.THIN);
        dataStyle.setBorderLeft(BorderStyle.THIN);
        dataStyle.setBorderRight(BorderStyle.THIN);

        Row headerRow = sheet.createRow(0);
        String[] encabezados = {"N° PRODUCTO", "Nombre", "Stock actual", "Cantidad vendidas"};

        for (int i = 0; i < encabezados.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(encabezados[i]);
            cell.setCellStyle(headerStyle);
            sheet.autoSizeColumn(i);
        }
        int rowNum = 1;
        for (ReportesVentasProductos r : reportes) {
            Row row = sheet.createRow(rowNum++);

            Cell idOrdenCell = row.createCell(0);
            idOrdenCell.setCellValue(r.getIdOrden());
            idOrdenCell.setCellStyle(dataStyle);

            Cell denominacionCell = row.createCell(1);
            denominacionCell.setCellValue(r.getDenominacion());
            denominacionCell.setCellStyle(productNameStyle);

            Cell stockCell = row.createCell(2);
            stockCell.setCellValue(r.getStock());
            stockCell.setCellStyle(productNameStyle);

            Cell cantidadVendidasCell = row.createCell(3);
            cantidadVendidasCell.setCellValue(r.getCantidadVendidas());
            cantidadVendidasCell.setCellStyle(numberStyle);

            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=reporte_ventas.xlsx";
        response.setHeader(headerKey, headerValue);

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}