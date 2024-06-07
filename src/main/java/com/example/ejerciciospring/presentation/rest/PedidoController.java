package com.example.ejerciciospring.presentation.rest;

import com.example.ejerciciospring.business.services.IInstrumentoService;
import com.example.ejerciciospring.business.services.IPedidoService;
import com.example.ejerciciospring.domain.entities.Instrumento;
import com.example.ejerciciospring.domain.entities.Pedido;
import com.example.ejerciciospring.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(value = "*",allowedHeaders = "*")
public class PedidoController {

    @Autowired
    private IPedidoService pedidoService;

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping("/group-by-month-year")
    public ResponseEntity<?> getPedidosGroupedByMonthYear() {
        List<Object[]> results = pedidoRepository.countPedidosGroupedByMonthYear();
        return ResponseEntity.ok(results);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getById(@PathVariable Long id){
        return ResponseEntity.ok().body(pedidoService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Pedido>> getAll() {
        try {
            var items = pedidoService.getAll();
            return ResponseEntity.ok().body(items);
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Pedido> crear(@RequestBody Pedido pedido){
        return ResponseEntity.ok().body(pedidoService.crear(pedido));
    }

    @PutMapping("/update")
    public ResponseEntity<Pedido> actualizar(@RequestBody Pedido pedido) {
        try {
            var updatedPedido = pedidoService.actualizar(pedido);
            return ResponseEntity.ok().body(updatedPedido);
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }


}
