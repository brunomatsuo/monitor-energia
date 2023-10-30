package com.fiap.challenge.monitorenergia.dominio.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.challenge.monitorenergia.dominio.entities.Endereco;
import com.fiap.challenge.monitorenergia.dominio.entities.Pessoa;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public class UsuarioDTO {
    @JsonProperty
    private Long id;
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

    @JsonProperty
    private Endereco endereco;

    @JsonProperty
    private List<PessoaDTO> pessoas;

    public UsuarioDTO(){}

    public UsuarioDTO(String nome, LocalDate dataNascimento, String sexo, String email, EnderecoDTO enderecoDTO) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<PessoaDTO> getPessoa() {
        return pessoas;
    }

    public void setPessoa(List<PessoaDTO> pessoas) {
        this.pessoas = pessoas;
    }
}
