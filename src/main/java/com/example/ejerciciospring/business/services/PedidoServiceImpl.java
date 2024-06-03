package com.example.ejerciciospring.business.services;


import com.example.ejerciciospring.domain.entities.Instrumento;
import com.example.ejerciciospring.domain.entities.Pedido;
import com.example.ejerciciospring.repositories.InstrumentoRepository;
import com.example.ejerciciospring.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServiceImpl implements IPedidoService{


    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    IInstrumentoService instrumentoService;

    @Override
    public Pedido crear(Pedido pedido) {

        //Esto solo sirve para actualizar la cantidad vendida del instrumento, no tiene que ver con el pedido
        pedido.getDetallesPedido().forEach(detallePedido -> {
            Instrumento instrumento = detallePedido.getInstrumento();
            System.out.println(instrumento.getInstrumento());

            // Obtener la cantidad vendida actual del instrumento
            String cantidadVendidaActual = instrumento.getCantidadVendida();

            // Actualizar la cantidad vendida con la cantidad del detalle del pedido
            int nuevaCantidadVendida = Integer.parseInt(cantidadVendidaActual) + detallePedido.getCantidad();
            instrumento.setCantidadVendida(String.valueOf(nuevaCantidadVendida));

            // Actualizar el instrumento en la base de datos
            instrumentoService.actualizar(instrumento);
        });

        //Esto es lo único que hace el service de pedido realmente
        return pedidoRepository.save(pedido);
    }


    @Override
    public Pedido getById(Long id){
        var pedido = pedidoRepository.findById(id);
        if(pedido.isEmpty()) throw new RuntimeException("No hay instrumentos");
        return pedido.get();
    }

    @Override
    public List<Pedido> getAll(){
        List<Pedido> pedidos = pedidoRepository.findAll();
        if(pedidos.isEmpty()) throw new RuntimeException("No se encontraron instrumentos");
        return pedidos;
    }

    @Override
    public Pedido actualizar(Pedido entity) {
        var optionalEntity = pedidoRepository.findById(entity.getId());
        if (optionalEntity.isEmpty()){
            throw new RuntimeException("No se encontro una entidad con el id " + entity.getId());
        }
        var newEntity = pedidoRepository.save(entity);
        return newEntity;
    }



}
