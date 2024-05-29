package com.example.ejerciciospring.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

    private Boolean activo;

    private String nombreUsuario;

    private String clave;

    private String rol;

}
