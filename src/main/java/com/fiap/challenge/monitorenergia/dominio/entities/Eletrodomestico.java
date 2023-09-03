package com.fiap.challenge.monitorenergia.dominio.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.challenge.monitorenergia.dominio.dto.EletrodomesticoDTO;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Eletrodomestico {
    @JsonProperty
    private String nome;
    @JsonProperty
    private String modelo;
    @JsonProperty
    private int potencia;
    @JsonProperty
    private int voltagem;

    public Eletrodomestico(String nome, String modelo, int potencia, int voltagem) {
        this.nome = nome;
        this.modelo = modelo;
        this.potencia = potencia;
        this.voltagem = voltagem;
    }

    public EletrodomesticoDTO toEletrodomesticoDTO(){
        return new EletrodomesticoDTO(nome, modelo, potencia, voltagem);
    }
}
