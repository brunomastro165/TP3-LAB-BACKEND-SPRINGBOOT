package com.example.ejerciciospring.business.mapper;

import com.example.ejerciciospring.domain.dtos.PersonaDTO;
import com.example.ejerciciospring.domain.dtos.PersonaShortDTO;
import com.example.ejerciciospring.domain.entities.Persona;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonaMapper {

    PersonaDTO personaToPersonaFullDTO(Persona persona);

    Persona personaFullDtoToPersona(PersonaDTO personaDTO);

    //Esto permite hacer un DTO de PERSONA con un solo dato de DOMICILIO
    @Mapping(source = "domicilio.provincia", target = "provincia")
    PersonaShortDTO personaToPersonaShortDto(Persona persona);


}
