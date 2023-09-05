package com.fiap.challenge.monitorenergia.dominio.repositorio;

import com.fiap.challenge.monitorenergia.dominio.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEnderecoRepository extends JpaRepository<Endereco, Long> {
}
