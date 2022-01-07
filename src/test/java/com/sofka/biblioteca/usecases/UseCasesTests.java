package com.sofka.biblioteca.usecases;

import com.sofka.biblioteca.DTOS.RecursoDTO;
import com.sofka.biblioteca.controllers.addResourceRouter;
import com.sofka.biblioteca.models.Recurso;
import com.sofka.biblioteca.repositories.RecursoRepository;
import com.sofka.biblioteca.utils.Mapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static reactor.core.publisher.Mono.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UseCasesTests {

    @MockBean
    RecursoRepository repository;

    Mapper mapper;

    @Test
    void idSisponibleTest() {
        var id = "1234";
        var recursoResponse = new Recurso("00/00/00", "xx", "yyy", "ttt");

        when(repository.findById(anyString())).thenReturn(Mono.just(recursoResponse));
        when(repository.save(any())).thenReturn(Mono.just(recursoResponse));
        IsDisponibleUseCase usecase = new IsDisponibleUseCase(mapper, repository);

        var disponible = usecase.apply(id);

        Assertions.assertEquals("No disponible desde 00/00/00", disponible.toString());
    }
}