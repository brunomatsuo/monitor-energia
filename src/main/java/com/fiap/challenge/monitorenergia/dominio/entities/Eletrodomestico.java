package com.fiap.challenge.monitorenergia.dominio.entities;

import jakarta.persistence.*;

import java.util.Objects;


@Entity
@Table(name = "tb_eletrodomesticos")
public class Eletrodomestico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String modelo;
    private int potencia;
    private int voltagem;

    public Eletrodomestico(){}

    public Eletrodomestico(String nome, String modelo, int potencia, int voltagem) {
        this.nome = nome;
        this.modelo = modelo;
        this.potencia = potencia;
        this.voltagem = voltagem;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public int getVoltagem() {
        return voltagem;
    }

    public void setVoltagem(int voltagem) {
        this.voltagem = voltagem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Eletrodomestico eletrodomestico = (Eletrodomestico) o;
        return Objects.equals(id, eletrodomestico.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
