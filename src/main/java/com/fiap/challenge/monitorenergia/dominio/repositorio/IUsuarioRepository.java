package com.fiap.challenge.monitorenergia.dominio.repositorio;

import com.fiap.challenge.monitorenergia.dominio.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
}
