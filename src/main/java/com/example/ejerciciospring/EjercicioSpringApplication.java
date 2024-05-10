package com.example.ejerciciospring;

import com.example.ejerciciospring.domain.entities.DetallePedido;
import com.example.ejerciciospring.domain.entities.Instrumento;
import com.example.ejerciciospring.domain.entities.Pedido;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class EjercicioSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(EjercicioSpringApplication.class, args);
        System.out.println("hola");

        Instrumento instrumento = new Instrumento();
        List<DetallePedido> lista = new ArrayList<>();
        DetallePedido detalle = new DetallePedido(1L,1,instrumento);
        lista.add(detalle);
        Pedido pedido = new Pedido();
        pedido.setDetallesPedido(lista);
    }

}
