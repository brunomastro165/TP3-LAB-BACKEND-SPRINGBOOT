package com.example.ejerciciospring.domain.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilioId", referencedColumnName = "id")
    private Domicilio domicilio;

    private String nombre;

    private String apellido;

    private Integer edad;

    private LocalDate fechaNacimiento;

}

