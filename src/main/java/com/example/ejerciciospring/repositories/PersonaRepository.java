package com.example.ejerciciospring.repositories;

import com.example.ejerciciospring.domain.entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
}
