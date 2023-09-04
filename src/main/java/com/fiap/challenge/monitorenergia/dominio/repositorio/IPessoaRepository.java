package com.fiap.challenge.monitorenergia.dominio.repositorio;

import com.fiap.challenge.monitorenergia.dominio.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPessoaRepository extends JpaRepository<Pessoa, Long> {
}
