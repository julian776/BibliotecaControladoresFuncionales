package com.sofka.biblioteca.usecases;

import com.sofka.biblioteca.repositories.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Validated
@Service
public class DevolverRecurso {

    @Autowired
    private final RecursoRepository repository;

    public DevolverRecurso(RecursoRepository repository){
        this.repository = repository;
    }

    public Mono<Object> apply(String id) {
        return repository.findById(id).flatMap(recurso -> recurso.isPrestado() ?
                repository.save(recurso.setPrestado(false)) : Mono.just("El recurso no se encuentra prestado"));
    }
}
