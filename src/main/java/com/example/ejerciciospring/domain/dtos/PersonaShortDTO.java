package com.example.ejerciciospring.domain.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonaShortDTO {
    private String nombre;
    private String apellido;
    private String provincia;
}
