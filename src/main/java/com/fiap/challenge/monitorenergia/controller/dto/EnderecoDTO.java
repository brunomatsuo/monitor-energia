package com.fiap.challenge.monitorenergia.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.challenge.monitorenergia.dominio.Endereco;
import com.fiap.challenge.monitorenergia.dominio.Usuario;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class EnderecoDTO {
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

    public EnderecoDTO(String rua, int numero, String complemento, String bairro, String cidade, String estado) {
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public Endereco toEndereco(){
        return new Endereco(rua, numero, complemento, bairro, cidade, estado);
    }
}
