package com.example.ejerciciospring.business.facade;

import com.example.ejerciciospring.domain.dtos.PersonaDTO;
import com.example.ejerciciospring.domain.dtos.PersonaShortDTO;

public interface IPersonaFacade {

    PersonaDTO crear(PersonaDTO nuevaPersonaDTO);

    PersonaDTO getAllDataByID(Long id);

    PersonaShortDTO getShortDataById(Long id);
}
