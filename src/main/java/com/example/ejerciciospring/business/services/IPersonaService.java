package com.example.ejerciciospring.business.services;

import com.example.ejerciciospring.domain.entities.Persona;

public interface IPersonaService {

    Persona crear(Persona persona);

    Persona getById(Long id);
}
