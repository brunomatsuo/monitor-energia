package com.fiap.challenge.monitorenergia.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.challenge.monitorenergia.controller.dto.EnderecoDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
public class Endereco {
    @JsonProperty
    private String rua;
    @JsonProperty
    private int numero;
    @JsonProperty
    private String complemento;
    @JsonProperty
    private String bairro;
    @JsonProperty
    private String cidade;
    @JsonProperty
    private String estado;

    public Endereco(String rua, int numero, String complemento, String bairro, String cidade, String estado) {
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public EnderecoDTO toEnderecoDTO(){
        return new EnderecoDTO(rua, numero, complemento, bairro, cidade, estado);
    }
}
