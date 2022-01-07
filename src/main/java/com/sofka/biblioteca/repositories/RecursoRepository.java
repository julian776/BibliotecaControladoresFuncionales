package com.sofka.biblioteca.repositories;

import com.sofka.biblioteca.models.Recurso;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface RecursoRepository extends ReactiveMongoRepository<Recurso, String> {

    Flux<Recurso> findAllByTipoAndTopic(String tipo, String topic);
}
