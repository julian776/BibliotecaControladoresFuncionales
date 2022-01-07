package com.sofka.biblioteca.usecases;

import com.sofka.biblioteca.DTOS.RecursoDTO;
import com.sofka.biblioteca.repositories.RecursoRepository;
import com.sofka.biblioteca.utils.Mapper;
import com.sofka.biblioteca.utils.saveRecurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class AddUseCase implements saveRecurso {

    private Mapper mapper;
    private RecursoRepository repository;

    @Autowired
    public AddUseCase(Mapper mapper, RecursoRepository repository){

        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public Mono<RecursoDTO> apply(RecursoDTO recursoDTO) {
        return repository.save(mapper.fromRecursoToEntity().apply(recursoDTO))
                .map(recurso -> mapper.fromEntityToRecurso().apply(recurso));
    }
}
