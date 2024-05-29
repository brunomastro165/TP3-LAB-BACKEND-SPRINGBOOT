package com.example.ejerciciospring.business.services;

import com.example.ejerciciospring.domain.entities.Instrumento;
import com.example.ejerciciospring.domain.entities.Usuario;
import com.example.ejerciciospring.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IUsuarioServiceImpl implements IUsuarioService{

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public Usuario crear(Usuario usuario) {return  usuarioRepository.save(usuario);}

    @Override
    public Usuario getById(Long id){
        var usuario = usuarioRepository.findById(id);
        if(usuario.isEmpty()) throw new RuntimeException("No hay usuarios");
        return usuario.get();
    }

    @Override
    public List<Usuario> getAll(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        if(usuarios.isEmpty()) throw new RuntimeException("No se encontraron usuarios");
        return usuarios;
    }

    @Override
    public Usuario actualizar(Usuario entity) {
        var optionalEntity = usuarioRepository.findById(entity.getId());
        if (optionalEntity.isEmpty()){
            throw new RuntimeException("No se encontro una entidad con el id " + entity.getId());
        }
        var newEntity = usuarioRepository.save(entity);
        return newEntity;
    }

    @Override
    public Usuario eliminar(Usuario entity) {
        var optionalEntity = usuarioRepository.findById(entity.getId());
        if (optionalEntity.isEmpty()){
            throw new RuntimeException("No se encontro una entidad con el id " + entity.getId());
        }
        var finalEntity = optionalEntity.get();
        finalEntity.setActivo(false);
        System.out.println(finalEntity.getActivo());
        var newEntity = usuarioRepository.save(finalEntity);
        return newEntity;
    }

}
