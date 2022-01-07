package com.sofka.biblioteca.usecases;

import com.sofka.biblioteca.repositories.RecursoRepository;
import com.sofka.biblioteca.utils.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Validated
@Service
public class IsDisponibleUseCase {

    private final Mapper mapper;
    private final RecursoRepository repository;

    public IsDisponibleUseCase(Mapper mapper, RecursoRepository repository){
        this.mapper = mapper;
        this.repository = repository;
    }

    public Mapper getMapper() {
        return mapper;
    }

    public RecursoRepository getRepository() {
        return repository;
    }

    public Mono<String> apply(String id){
        return repository.findById(id)
                .map(recurso -> recurso.isPrestado() ? "No disponible desde "+recurso.getFechaSalida() : "Disponible");
    }
}
