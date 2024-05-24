package com.example.ejerciciospring.presentation.rest;

import com.example.ejerciciospring.business.services.IInstrumentoService;
import com.example.ejerciciospring.domain.entities.Instrumento;
import com.example.ejerciciospring.domain.entities.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/instrumentos")
@CrossOrigin(value = "*",allowedHeaders = "*")
public class InstrumentoController {

    @Autowired
    private IInstrumentoService instrumentoService;

    @GetMapping("/{id}")
    public ResponseEntity<Instrumento> getById(@PathVariable Long id){
        return ResponseEntity.ok().body(instrumentoService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Instrumento>> getAll() {
        try {
            var items = instrumentoService.getAll();
            var filteredItems = items.stream()
                    .filter(Instrumento::getActivo)
                    .toList();
            return ResponseEntity.ok().body(filteredItems);
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Instrumento> crear(@RequestBody Instrumento instrumento){
        return ResponseEntity.ok().body(instrumentoService.crear(instrumento));
    }

    @PutMapping("/update")
    public ResponseEntity<Instrumento> actualizar(@RequestBody Instrumento instrumento) {
        System.out.println(instrumento.getInstrumento());
        try {
            var updatedInstrumento = instrumentoService.actualizar(instrumento);
            return ResponseEntity.ok().body(updatedInstrumento);
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }

    @PutMapping("/delete")
    public ResponseEntity<Instrumento> eliminar(@RequestBody Instrumento instrumento) {
        System.out.println(instrumento.getInstrumento());
        try {
            var updatedInstrumento = instrumentoService.eliminar(instrumento);
            return ResponseEntity.ok().body(updatedInstrumento);
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }

    @PostMapping("/api/create_preference_mp")
    public PreferenceMP creaarPreferenciaMercadoPago(@RequestBody Pedido pedido){
        System.out.println("Hola");
        MercadoPagoController cMercadoPago = new MercadoPagoController();
        PreferenceMP preference = cMercadoPago.getPreferenciaIdMercadoPago(pedido);
        return preference;
    }

}
