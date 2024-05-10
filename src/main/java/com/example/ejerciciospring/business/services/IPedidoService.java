package com.example.ejerciciospring.business.services;

import com.example.ejerciciospring.domain.entities.Instrumento;
import com.example.ejerciciospring.domain.entities.Pedido;

import java.util.List;

public interface IPedidoService {

    Pedido crear(Pedido pedido);

    Pedido getById(Long id);

    List<Pedido> getAll();

    Pedido actualizar(Pedido pedido);


}
