package com.sofka.biblioteca.utils;

import reactor.core.publisher.Mono;

public interface deleteRecurso {

    Mono<Void> apply(String id);
}
