package com.sofka.biblioteca.utils;

import com.sofka.biblioteca.DTOS.RecursoDTO;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface saveRecurso {

    Mono<RecursoDTO> apply(RecursoDTO recursoDTO);
}
