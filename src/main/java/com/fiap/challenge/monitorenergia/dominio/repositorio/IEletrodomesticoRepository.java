package com.fiap.challenge.monitorenergia.dominio.repositorio;

import com.fiap.challenge.monitorenergia.dominio.entities.Eletrodomestico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEletrodomesticoRepository extends JpaRepository<Eletrodomestico, Long> {
}
