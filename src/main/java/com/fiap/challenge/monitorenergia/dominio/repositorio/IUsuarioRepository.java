package com.fiap.challenge.monitorenergia.dominio.repositorio;

import com.fiap.challenge.monitorenergia.dominio.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

    public Usuario findByNome(String nome);
}
