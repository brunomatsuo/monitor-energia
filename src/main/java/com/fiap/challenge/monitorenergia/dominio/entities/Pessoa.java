package com.fiap.challenge.monitorenergia.dominio.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.challenge.monitorenergia.dominio.dto.PessoaDTO;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode
public class Pessoa {
    @JsonProperty
    private String nome;
    @JsonProperty
    private LocalDate dataNascimento;
    @JsonProperty
    private String sexo;
    @JsonProperty
    private String parentesco;

    public Pessoa(String nome, LocalDate dataNascimento, String sexo, String parentesco) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.parentesco = parentesco;
    }

    public PessoaDTO toPessoaDto(){
        return new PessoaDTO(nome, dataNascimento, sexo, parentesco);
    }
}
