package com.fiap.challenge.monitorenergia.dominio.repositorio;

import com.fiap.challenge.monitorenergia.dominio.entities.Pessoa;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class RepositorioPessoa {

    private Set<Pessoa> pessoas;

    public RepositorioPessoa(){
        pessoas = new HashSet<>();
    }

    public void salvar(Pessoa pessoa){
        pessoas.add(pessoa);
    }

    public Set<Pessoa> getPessoas(){
        return pessoas;
    }

    public boolean removerPessoa(Pessoa pessoa) {
        if(!pessoas.contains(pessoa)){
            return false;
        }
        pessoas.remove(pessoa);
        return true;
    }
}
