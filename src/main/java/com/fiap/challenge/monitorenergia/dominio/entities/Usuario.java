package com.fiap.challenge.monitorenergia.dominio.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.challenge.monitorenergia.dominio.dto.UsuarioDTO;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode
public class Usuario {
    @JsonProperty
    private String nome;
    @JsonProperty
    private LocalDate dataNascimento;
    @JsonProperty
    private String sexo;
    @JsonProperty
    private String email;

    public Usuario(String nome, LocalDate dataNascimento, String sexo, String email) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.email = email;
    }

    public UsuarioDTO toUsuarioDto(){
        return new UsuarioDTO(nome, dataNascimento, sexo, email);
    }
}
