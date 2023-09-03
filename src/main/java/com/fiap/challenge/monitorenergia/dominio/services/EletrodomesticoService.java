package com.fiap.challenge.monitorenergia.dominio.services;

import com.fiap.challenge.monitorenergia.dominio.dto.EletrodomesticoDTO;
import com.fiap.challenge.monitorenergia.dominio.entities.Eletrodomestico;
import com.fiap.challenge.monitorenergia.dominio.repositorio.IEletrodomesticoRepository;
import com.fiap.challenge.monitorenergia.exception.service.ControllerNotFoundException;
import com.fiap.challenge.monitorenergia.exception.service.DatabaseException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class EletrodomesticoService {

    @Autowired
    private IEletrodomesticoRepository repository;

    public Page<EletrodomesticoDTO> findAll(PageRequest pageRequest) {
        Page<Eletrodomestico> list = repository.findAll(pageRequest);
        return list.map(x -> new EletrodomesticoDTO(x));
    }

    public EletrodomesticoDTO findById(Long id) {
        Optional<Eletrodomestico> entity = repository.findById(id);
        Eletrodomestico eletrodomestico = entity.orElseThrow(() -> new ControllerNotFoundException("Eletrodoméstico não encontrado"));
        return new EletrodomesticoDTO(eletrodomestico);
    }

    public EletrodomesticoDTO insert(EletrodomesticoDTO dto) {
        try {
            Eletrodomestico entity = dto.toEletrodomestico();
            entity = repository.save(entity);

            return  new EletrodomesticoDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Eletrodoméstico não encontrado");
        }
    }

    public EletrodomesticoDTO update(Long id, EletrodomesticoDTO dto) {
        try {
            Eletrodomestico entity = repository.getReferenceById(id);
            entity = dto.toEletrodomestico();
            entity = repository.save(entity);

            return new EletrodomesticoDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Eletrodoméstico não encontrado");
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ControllerNotFoundException("Eletrodoméstico não encontrado");
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade dos dados");
        }
    }

}
