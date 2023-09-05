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
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class EletrodomesticoService {

    @Autowired
    private IEletrodomesticoRepository repository;

    public Page<EletrodomesticoDTO> findAll(PageRequest pageRequest) {
        Page<Eletrodomestico> list = repository.findAll(pageRequest);
        return list.map(this::mapperEntityToDto);
    }

    public EletrodomesticoDTO findById(Long id) {
        Optional<Eletrodomestico> entity = repository.findById(id);
        Eletrodomestico eletrodomestico = entity.orElseThrow(() -> new ControllerNotFoundException("Eletrodoméstico não encontrado"));
        return mapperEntityToDto(eletrodomestico);
    }

    public EletrodomesticoDTO insert(EletrodomesticoDTO dto) {
        try {
            Eletrodomestico eletrodomestico = new Eletrodomestico();
            mapperDtoToEntity(dto, eletrodomestico);
            eletrodomestico = repository.save(eletrodomestico);

            return  mapperEntityToDto(eletrodomestico);
        }
        catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Eletrodoméstico não encontrado");
        }
    }

    public EletrodomesticoDTO update(Long id, EletrodomesticoDTO dto) {
        try {
            Eletrodomestico eletrodomestico = repository.getReferenceById(id);

            mapperDtoToEntity(dto, eletrodomestico);

            eletrodomestico = repository.save(eletrodomestico);

            return mapperEntityToDto(eletrodomestico);
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

    private void mapperDtoToEntity(EletrodomesticoDTO dto, Eletrodomestico eletrodomestico){
        eletrodomestico.setNome(dto.getNome());
        eletrodomestico.setModelo(dto.getModelo());
        eletrodomestico.setVoltagem(dto.getVoltagem());
        eletrodomestico.setPotencia(dto.getPotencia());
    }

    private EletrodomesticoDTO mapperEntityToDto(Eletrodomestico eletrodomestico){
        EletrodomesticoDTO dto = new EletrodomesticoDTO();
        dto.setId(eletrodomestico.getId());
        dto.setNome(eletrodomestico.getNome());
        dto.setModelo(eletrodomestico.getModelo());
        dto.setPotencia(eletrodomestico.getPotencia());
        dto.setVoltagem(eletrodomestico.getVoltagem());

        return dto;
    }

}
