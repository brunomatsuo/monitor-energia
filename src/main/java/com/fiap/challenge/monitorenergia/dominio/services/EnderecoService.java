package com.fiap.challenge.monitorenergia.dominio.services;

import com.fiap.challenge.monitorenergia.dominio.dto.EnderecoDTO;
import com.fiap.challenge.monitorenergia.dominio.entities.Endereco;
import com.fiap.challenge.monitorenergia.dominio.entities.Usuario;
import com.fiap.challenge.monitorenergia.dominio.repositorio.IEnderecoRepository;
import com.fiap.challenge.monitorenergia.exception.service.ControllerNotFoundException;
import com.fiap.challenge.monitorenergia.exception.service.DatabaseException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class EnderecoService {

    @Autowired
    private IEnderecoRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    public Page<EnderecoDTO> findAll(PageRequest pageRequest){
        Page<Endereco> list = repository.findAll(pageRequest);
        return list.map(this::mapperEntityToDto);
    }

    public EnderecoDTO findById(Long id) {
        Optional<Endereco> entity = repository.findById(id);
        Endereco endereco = entity.orElseThrow(() -> new ControllerNotFoundException("Endereço não encontrado"));
        return mapperEntityToDto(endereco);
    }

    public EnderecoDTO insert(EnderecoDTO dto) {
        try {
            Endereco endereco = new Endereco();

            Usuario usuario = usuarioService.findByName(dto.getUsuario());

            mapperDtoToEntity(dto, endereco, usuario);
            endereco = repository.save(endereco);

            return  mapperEntityToDto(endereco);
        }
        catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Endereço não encontrado");
        }
    }

    public EnderecoDTO update(Long id, EnderecoDTO dto) {
        try {
            Endereco endereco = repository.getReferenceById(id);

            mapperDtoToEntity(dto, endereco);

            endereco = repository.save(endereco);

            return mapperEntityToDto(endereco);
        }
        catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Endereço não encontrado");
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ControllerNotFoundException("Endereço não encontrado");
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade dos dados");
        }
    }


    private void mapperDtoToEntity(EnderecoDTO dto, Endereco endereco){
        endereco.setRua(dto.getRua());
        endereco.setNumero(dto.getNumero());
        endereco.setComplemento(dto.getComplemento());
        endereco.setBairro(dto.getBairro());
        endereco.setCidade(dto.getCidade());
        endereco.setEstado(dto.getEstado());
    }

    private void mapperDtoToEntity(EnderecoDTO dto, Endereco endereco, Usuario usuario){
        endereco.setRua(dto.getRua());
        endereco.setNumero(dto.getNumero());
        endereco.setComplemento(dto.getComplemento());
        endereco.setBairro(dto.getBairro());
        endereco.setCidade(dto.getCidade());
        endereco.setEstado(dto.getEstado());
        endereco.setUsuario(usuario);
    }

    private EnderecoDTO mapperEntityToDto(Endereco endereco){
        EnderecoDTO dto = new EnderecoDTO();
        dto.setId(endereco.getId());
        dto.setRua(endereco.getRua());
        dto.setNumero(endereco.getNumero());
        dto.setComplemento(endereco.getComplemento());
        dto.setBairro(endereco.getBairro());
        dto.setCidade(endereco.getCidade());
        dto.setEstado(endereco.getEstado());
        dto.setUsuario(endereco.getUsuario().getNome());

        return dto;
    }

}
