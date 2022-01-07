package com.sofka.biblioteca.usecases;

import com.sofka.biblioteca.repositories.RecursoRepository;
import com.sofka.biblioteca.utils.Mapper;
import com.sofka.biblioteca.utils.deleteRecurso;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Validated
@Service
public class EliminarRecurso implements deleteRecurso {

    private final Mapper mapper;
    private final RecursoRepository repository;

    public EliminarRecurso(Mapper mapper, RecursoRepository repository){

        this.mapper = mapper;
        this.repository = repository;
    }

    public Mapper getMapper() {
        return mapper;
    }

    public RecursoRepository getRepository() {
        return repository;
    }

    @Override
    public Mono<Void> apply(String id) {
        return repository.deleteById(id);
    }
}
