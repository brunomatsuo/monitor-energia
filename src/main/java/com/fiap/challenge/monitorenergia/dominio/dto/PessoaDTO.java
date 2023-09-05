package com.fiap.challenge.monitorenergia.dominio.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public class PessoaDTO {
    @JsonProperty
    private Long id;
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

    private String usuario;

    public PessoaDTO(){}

    public PessoaDTO(Long id, String nome, LocalDate dataNascimento, String sexo, String parentesco) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.parentesco = parentesco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
