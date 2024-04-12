package com.example.ejerciciospring.business.services;

import com.example.ejerciciospring.domain.entities.Instrumento;
import com.example.ejerciciospring.repositories.InstrumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstrumentoServiceImpl implements IInstrumentoService{


    @Autowired
    InstrumentoRepository instrumentoRepository;

    @Override
    public Instrumento crear(Instrumento instrumento) {return  instrumentoRepository.save(instrumento);}


    @Override
    public Instrumento getById(Long id){
        var instrumento = instrumentoRepository.findById(id);
        if(instrumento.isEmpty()) throw new RuntimeException("No hay instrumentos");
        return instrumento.get();
    }

    @Override
    public List<Instrumento> getAll(){
        List<Instrumento> instrumentos = instrumentoRepository.findAll();
        if(instrumentos.isEmpty()) throw new RuntimeException("No se encontraron instrumentos");
        return instrumentos;
    }


}
