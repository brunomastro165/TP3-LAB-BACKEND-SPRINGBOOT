package com.example.ejerciciospring.repositories;

import com.example.ejerciciospring.domain.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
