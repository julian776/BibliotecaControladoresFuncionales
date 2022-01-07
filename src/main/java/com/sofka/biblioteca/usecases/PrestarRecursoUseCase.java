package com.sofka.biblioteca.usecases;

import com.sofka.biblioteca.repositories.RecursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Validated
@Service
public class PrestarRecursoUseCase {

    private final RecursoRepository repository;

    public PrestarRecursoUseCase(RecursoRepository repository){
        this.repository = repository;
    }

    public Mono<Object> apply(String id){
        return repository.findById(id).flatMap(recurso ->  recurso.isPrestado() ? Mono.just("El recurso ya esta prestado") : repository.save(recurso.setPrestado(true)));
    }
}
