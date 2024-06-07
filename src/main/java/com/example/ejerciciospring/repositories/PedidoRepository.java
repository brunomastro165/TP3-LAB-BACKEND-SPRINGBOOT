package com.example.ejerciciospring.repositories;

import com.example.ejerciciospring.domain.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("SELECT YEAR(p.fecha), MONTH(p.fecha), COUNT(p) " +
            "FROM Pedido p GROUP BY YEAR(p.fecha), MONTH(p.fecha)")
    List<Object[]> countPedidosGroupedByMonthYear();

}
