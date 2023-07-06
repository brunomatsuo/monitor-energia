package com.fiap.challenge.monitorenergia.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.challenge.monitorenergia.dominio.Pessoa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public class PessoaDTO {
    @JsonProperty
    @NotBlank(message = "O nome é obrigatório e não pode ser nulo.")
    private String nome;
    @JsonProperty
    @NotNull(message = "A data de nascimento é obrigatória e não pode ser nula.")
    @Past(message = "A data de nascimento deve ser uma data válida")
    private LocalDate dataNascimento;
    @JsonProperty
    @NotBlank(message = "O sexo é obrigatório e não pode ser nulo.")
    private String sexo;
    @JsonProperty
    @NotBlank(message = "O grau de parentesco com o usuário é obrigatório e não pode ser nulo.")
    private String parentesco;

    public PessoaDTO(String nome, LocalDate dataNascimento, String sexo, String parentesco) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.parentesco = parentesco;
    }

    public Pessoa toPessoa(){
        return new Pessoa(nome, dataNascimento, sexo, parentesco);
    }
}
