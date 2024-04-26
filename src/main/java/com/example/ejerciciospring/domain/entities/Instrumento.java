package com.example.ejerciciospring.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Instrumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String instrumento;

    private String marca;

    private String modelo;

    @Column(name = "imagen",length = 1024)
    private String imagen;

    private String precio;

    private String costoEnvio;

    private String cantidadVendida;

    private Boolean activo;

    @Column(name = "descripcion",length = 1024)
    private String descripcion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoriaId")
    private Categoria categoria;
}
