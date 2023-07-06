package com.fiap.challenge.monitorenergia.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.challenge.monitorenergia.dominio.Usuario;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class UsuarioDTO {
    @JsonProperty
    @NotBlank(message = "O nome é obrigatório e não pode ser nulo.")
    private String nome;
    @JsonProperty
    @NotNull(message = "A data de nascimento é obrigatória e não pode ser nula.")
    @Past(message = "A data de nascimento deve ser uma data válida.")
    private LocalDate dataNascimento;
    @JsonProperty
    @NotBlank(message = "O sexo é obrigatório e não pode ser nulo.")
    private String sexo;
    @JsonProperty
    @Email(message = "Formato de e-mail incorreto.")
    @NotBlank(message = "O e-mail é obrigatório e não pode ser nulo.")
    private String email;

    public UsuarioDTO(String nome, LocalDate dataNascimento, String sexo, String email) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.email = email;
    }

    public Usuario toUsuario(){
        return new Usuario(nome, dataNascimento, sexo, email);
    }
}
