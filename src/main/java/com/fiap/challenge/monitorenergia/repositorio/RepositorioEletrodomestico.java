package com.fiap.challenge.monitorenergia.repositorio;

import com.fiap.challenge.monitorenergia.dominio.Eletrodomestico;
import org.springframework.stereotype.Repository;
import java.util.HashSet;
import java.util.Set;

@Repository
public class RepositorioEletrodomestico {

    private Set<Eletrodomestico> eletrodomesticos;

    public RepositorioEletrodomestico() {
        eletrodomesticos = new HashSet<>();
    }

    public void salvar(Eletrodomestico eletrodomestico) {
        eletrodomesticos.add(eletrodomestico);
    }

    public Set<Eletrodomestico> getEletrodomesticos() {
        return eletrodomesticos;
    }

    public boolean removerEletrodomestico(Eletrodomestico eletrodomestico) {
        if(!eletrodomesticos.contains(eletrodomestico)){
            return false;
        }
        eletrodomesticos.remove(eletrodomestico);
        return true;
    }
}
