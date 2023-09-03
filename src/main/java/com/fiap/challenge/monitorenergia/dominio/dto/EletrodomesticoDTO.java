package com.fiap.challenge.monitorenergia.dominio.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.challenge.monitorenergia.dominio.entities.Eletrodomestico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class EletrodomesticoDTO {
    @JsonProperty
    private Long id;
    @JsonProperty
    @NotBlank(message = "O nome do eletrodoméstico é obrigatório e não pode ser nulo.")
    private String nome;
    @JsonProperty
    @NotBlank(message = "O modelo do eletrodoméstico é obrigatório e não pode ser nulo.")
    private String modelo;
    @JsonProperty
    @NotNull(message = "A potência do eletrodoméstico é obrigatória e não pode ser nulo.")
    @Positive(message = "Insira uma potência válida.")
    private int potencia;
    @JsonProperty
    @NotNull(message = "A voltagem do eletrodoméstico é obrigatória e não pode ser nulo.")
    @Positive(message = "Insira uma voltagem válida.")
    private int voltagem;

    public EletrodomesticoDTO(){}

    public EletrodomesticoDTO(Long id, String nome, String modelo, int potencia, int voltagem) {
        this.id = id;
        this.nome = nome;
        this.modelo = modelo;
        this.potencia = potencia;
        this.voltagem = voltagem;
    }

    public EletrodomesticoDTO(Eletrodomestico eletrodomestico) {
        this.id = eletrodomestico.getId();
        this.nome = eletrodomestico.getNome();
        this.modelo = eletrodomestico.getModelo();
        this.potencia = eletrodomestico.getPotencia();
        this.voltagem = eletrodomestico.getVoltagem();
    }

    public Long getId() {
        return id;
    }

    public Eletrodomestico toEletrodomestico(){
        return new Eletrodomestico(id, nome, modelo, potencia, voltagem);
    }
}
