package com.fiap.challenge.monitorenergia.dominio.repositorio;

import com.fiap.challenge.monitorenergia.dominio.entities.Endereco;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class RepositorioEndereco {
    private Set<Endereco> enderecos;

    public RepositorioEndereco(){
        enderecos = new HashSet<>();
    }

    public void salvar(Endereco endereco){
        enderecos.add(endereco);
    }

    public Set<Endereco> getEnderecos(){
        return enderecos;
    }

    public boolean removerEndereco(Endereco endereco) {
        if(!enderecos.contains(endereco)){
            return false;
        }
        enderecos.remove(endereco);
        return true;
    }
}
