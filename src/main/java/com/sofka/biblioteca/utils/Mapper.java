package com.sofka.biblioteca.utils;

import com.sofka.biblioteca.DTOS.RecursoDTO;
import com.sofka.biblioteca.models.Recurso;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class Mapper {

    public Function<Recurso, RecursoDTO> fromEntityToRecurso(){
            return entity -> new RecursoDTO(
                    entity.getFechaSalida(),
                    entity.getTitulo(),
                    entity.getTipo(),
                    entity.getTopic()
            );
    }

    public Function<RecursoDTO, Recurso> fromRecursoToEntity(){
        return recursoDTO -> new Recurso(
                recursoDTO.getFechaSalida(),
                recursoDTO.getTitulo(),
                recursoDTO.getTipo(),
                recursoDTO.getTopic()
        );
    }
}
