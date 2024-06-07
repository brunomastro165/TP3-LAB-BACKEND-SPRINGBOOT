package com.example.ejerciciospring.repositories;

import com.example.ejerciciospring.domain.entities.Instrumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InstrumentoRepository extends JpaRepository<Instrumento, Long> {

    @Query("SELECT i.instrumento, SUM(d.cantidad) FROM Pedido p " +
            "JOIN p.detallesPedido d JOIN d.instrumento i GROUP BY i.instrumento")
    List<Object[]> countPedidosGroupedByInstrument();
}
