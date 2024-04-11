package com.example.ejerciciospring.business.facade;

import com.example.ejerciciospring.business.mapper.PersonaMapper;
import com.example.ejerciciospring.business.services.IPersonaService;
import com.example.ejerciciospring.domain.dtos.PersonaDTO;
import com.example.ejerciciospring.domain.dtos.PersonaShortDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaFacadeImpl implements IPersonaFacade{

    @Autowired
    private IPersonaService personaService;

    @Autowired
    private PersonaMapper personaMapper;

    @Override
    public PersonaDTO crear(PersonaDTO nuevaPersonaDTO){
        var newPersona = personaMapper.personaFullDtoToPersona(nuevaPersonaDTO);
        var personaGuardada = personaService.crear(newPersona);
        return personaMapper.personaToPersonaFullDTO(personaGuardada);
    }

    @Override
    public PersonaDTO getAllDataByID(Long id){
        var persona = personaService.getById(id);
        return personaMapper.personaToPersonaFullDTO(persona);
    }

    @Override
    public PersonaShortDTO getShortDataById(Long id){
        var persona = personaService.getById(id);
        return personaMapper.personaToPersonaShortDto(persona);
    }

}
