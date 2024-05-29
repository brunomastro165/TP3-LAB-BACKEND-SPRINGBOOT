package com.example.ejerciciospring.repositories;

import com.example.ejerciciospring.domain.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
