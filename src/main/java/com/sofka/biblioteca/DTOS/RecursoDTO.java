package com.sofka.biblioteca.DTOS;

public class  RecursoDTO {

    private String fechaSalida;
    private String titulo;
    private String tipo;
    private String topic;

    public RecursoDTO(String fechaSalida, String titulo, String tipo, String topic){

        this.fechaSalida = fechaSalida;
        this.titulo = titulo;
        this.tipo = tipo;
        this.topic = topic;
    }

    public RecursoDTO(){}

    public String getTopic() {
        return topic;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getTipo() {
        return tipo;
    }
}
