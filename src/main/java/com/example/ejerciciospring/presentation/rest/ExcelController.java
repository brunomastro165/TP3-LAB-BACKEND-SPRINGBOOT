package com.example.ejerciciospring.presentation.rest;


import com.example.ejerciciospring.business.services.IPedidoService;
import com.example.ejerciciospring.domain.entities.DetallePedido;
import com.example.ejerciciospring.domain.entities.Instrumento;
import com.example.ejerciciospring.domain.entities.Pedido;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/excel")
@CrossOrigin(value = "*",allowedHeaders = "*")
public class ExcelController {


    @Autowired
    IPedidoService pedidoService;

    @GetMapping("/export")
    public ResponseEntity<Resource> exportToExcel(@RequestParam("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio,
                                                  @RequestParam("fechaFin") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin) {
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Pedidos");

            // Filtrar los pedidos por fecha
            List<Pedido> pedidos = pedidoService.getAll().stream()
                    .filter(pedido -> !pedido.getFecha().before(fechaInicio) && !pedido.getFecha().after(fechaFin))
                    .collect(Collectors.toList());

            // Crear la fila de encabezado
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Fecha");
            headerRow.createCell(1).setCellValue("Pedido");
            headerRow.createCell(2).setCellValue("Instrumento");
            headerRow.createCell(3).setCellValue("Marca");
            headerRow.createCell(4).setCellValue("Modelo");
            headerRow.createCell(5).setCellValue("Cantidad");
            headerRow.createCell(6).setCellValue("Precio");
            headerRow.createCell(7).setCellValue("Subtotal");

            // Crear las filas de datos
            int rowNum = 1;
            for (Pedido pedido : pedidos) {
                for (DetallePedido detalle : pedido.getDetallesPedido()) {
                    Instrumento instrumento = detalle.getInstrumento();
                    Row row = sheet.createRow(rowNum++);

                    row.createCell(0).setCellValue(pedido.getFecha().toString());
                    row.createCell(1).setCellValue(pedido.getTitulo());
                    row.createCell(2).setCellValue(instrumento.getInstrumento());
                    row.createCell(3).setCellValue(instrumento.getMarca());
                    row.createCell(4).setCellValue(instrumento.getModelo());
                    row.createCell(5).setCellValue(detalle.getCantidad());
                    row.createCell(6).setCellValue(Double.parseDouble(instrumento.getPrecio()));
                    row.createCell(7).setCellValue(detalle.getCantidad() * Double.parseDouble(instrumento.getPrecio()));
                }
            }


            // Convertir el workbook a un recurso de ByteArray
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            workbook.close();

            ByteArrayResource resource = new ByteArrayResource(out.toByteArray());

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=pedidos.xlsx")
                    .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .body(resource);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

