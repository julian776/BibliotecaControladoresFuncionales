package com.sofka.biblioteca.usecases;

import com.sofka.biblioteca.DTOS.RecursoDTO;
import com.sofka.biblioteca.repositories.RecursoRepository;
import com.sofka.biblioteca.utils.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.Optional;

@Service
@Validated
public class FindByTipoAndTopic {

    private Mapper mapper;
    private RecursoRepository repository;

    public FindByTipoAndTopic(Mapper mapper, RecursoRepository repository) {

        this.mapper = mapper;
        this.repository = repository;
    }

    public Flux<RecursoDTO> apply(Optional<String> tipo, Optional<String>  topic){
        var tipoVerified = tipo.orElse("");
        var topicVerified = topic.orElse("");
        return repository.findAllByTipoAndTopic(tipoVerified, topicVerified).map(recurso ->
                mapper.fromEntityToRecurso().apply(recurso));
    }
}
