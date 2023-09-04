package com.fiap.challenge.monitorenergia.dominio.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class EnderecoDTO {
    @JsonProperty
    private Long id;
    @JsonProperty
    @NotBlank(message = "O nome da rua é obrigatório e não pode ser nulo.")
    private String rua;
    @JsonProperty
    @Positive(message = "O número deve ser válido.")
    private int numero;
    @JsonProperty
    private String complemento;
    @JsonProperty
    @NotBlank(message = "O bairro é obrigatório e não pode ser nulo.")
    private String bairro;
    @JsonProperty
    @NotBlank(message = "A cidade é obrigatória e não pode ser nula.")
    private String cidade;
    @JsonProperty
    @NotBlank(message = "O estado é obrigatório e não pode ser nulo.")
    private String estado;

    public EnderecoDTO(){}

    public EnderecoDTO(String rua, int numero, String complemento, String bairro, String cidade, String estado) {
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
