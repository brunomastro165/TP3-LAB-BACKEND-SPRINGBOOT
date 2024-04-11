package com.example.ejerciciospring.presentation.rest;

import com.example.ejerciciospring.business.facade.IPersonaFacade;
import com.example.ejerciciospring.domain.dtos.PersonaDTO;
import com.example.ejerciciospring.domain.dtos.PersonaShortDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private IPersonaFacade personaFacade;

    @GetMapping("/all/{id}")
    public ResponseEntity<PersonaDTO> getAllDataById(@PathVariable Long id){
        return ResponseEntity.ok().body(personaFacade.getAllDataByID(id));
    }

    @GetMapping("/short/{id}")
    public ResponseEntity<PersonaShortDTO> getShortDataById(@PathVariable Long id){
        return ResponseEntity.ok().body(personaFacade.getShortDataById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<PersonaDTO> crear(@RequestBody PersonaDTO personaDTO){
        return ResponseEntity.ok().body(personaFacade.crear(personaDTO));
    }


}
