package com.example.ejerciciospring.business.services;

import com.example.ejerciciospring.domain.entities.Persona;
import com.example.ejerciciospring.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl implements IPersonaService{
    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public Persona crear(Persona persona){
        return personaRepository.save(persona);
    }

    @Override
    public Persona getById(Long id){
        var persona  = personaRepository.findById(id);
        if(persona.isEmpty()) throw new RuntimeException("No hay ninguna persona");
        return persona.get();
    }
}
