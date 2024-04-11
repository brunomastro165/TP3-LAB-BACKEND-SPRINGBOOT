package com.example.ejerciciospring.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonaDTO {

    private Long id;

    private DomicilioDTO domicilio;

    private String nombre;

    private String apellido;

    private Integer edad;

    private LocalDate fechaNacimiento;
}
