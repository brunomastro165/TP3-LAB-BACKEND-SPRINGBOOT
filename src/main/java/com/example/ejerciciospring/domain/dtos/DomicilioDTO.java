package com.example.ejerciciospring.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DomicilioDTO {

    private Long id;
    private String calle;
    private Long numero;
    private String provincia;
}
