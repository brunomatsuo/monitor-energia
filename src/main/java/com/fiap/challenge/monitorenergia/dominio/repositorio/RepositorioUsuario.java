package com.fiap.challenge.monitorenergia.dominio.repositorio;

import com.fiap.challenge.monitorenergia.dominio.entities.Usuario;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class RepositorioUsuario {

    private Set<Usuario> usuarios;

    public RepositorioUsuario(){
        usuarios = new HashSet<>();
    }

    public void salvar(Usuario usuario){
        usuarios.add(usuario);
    }

    public Set<Usuario> getUsuarios(){
        return usuarios;
    }

    public boolean removerUsuario(Usuario usuario) {
        if(!usuarios.contains(usuario))
        {
            return false;
        }
        usuarios.remove(usuario);
        return true;
    }
}
