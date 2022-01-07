package com.sofka.biblioteca.controllers;

import com.sofka.biblioteca.DTOS.RecursoDTO;
import com.sofka.biblioteca.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class addResourceRouter {

    @Bean
    public RouterFunction<ServerResponse> addResource(AddUseCase addUseCase){
        return route(POST("/add").and(accept(MediaType.APPLICATION_JSON)), //NextLine
                request -> request.bodyToMono(RecursoDTO.class).flatMap(
                    recursoDTO -> addUseCase.apply(recursoDTO)
                            .flatMap(resultado -> ServerResponse.ok()
                                    .bodyValue(resultado))
            )
        );
    }

    @Bean
    public RouterFunction<ServerResponse> consultarDisponibilidad(IsDisponibleUseCase usecase){
        return route(GET("/disponibilidad/{id}"), request ->
                usecase.apply(request.pathVariable("id"))
                        .flatMap(disponibilidad -> ServerResponse.ok().bodyValue(disponibilidad)));
    }

    @Bean
    public RouterFunction<ServerResponse> deleteRecurso(EliminarRecurso usecase){
        return route(DELETE("/eliminar/{id}"), request ->
                usecase.apply(request.pathVariable("id"))
                        .flatMap(respuesta -> ServerResponse.ok().bodyValue("Succesful eliminated")));
    }

    @Bean
    RouterFunction<ServerResponse> prestarRecurso(PrestarRecursoUseCase usecase){
        return route(PUT("/prestar/{id}"), request -> ServerResponse.ok()
                .body(BodyInserters.fromPublisher(usecase.apply(request.pathVariable("id")), Object.class)));
    }

    @Bean
    RouterFunction<ServerResponse> regresarRecurso(DevolverRecurso usecase){
        return route(PUT("/devolver/{id}"), request -> ServerResponse.ok()
                .body(BodyInserters.fromPublisher(usecase.apply(request.pathVariable("id")), Object.class)));
    }

    @Bean
    RouterFunction<ServerResponse> findResourcesTipoAndTopic(FindByTipoAndTopic findByTipoAndTopic){
        return route(GET("/filter").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(findByTipoAndTopic.apply(request.queryParam("tipo"), request.queryParam("topic")), RecursoDTO.class))
        );
    }
}
