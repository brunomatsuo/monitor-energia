package com.fiap.challenge.monitorenergia.dominio.services;

import com.fiap.challenge.monitorenergia.dominio.dto.EnderecoDTO;
import com.fiap.challenge.monitorenergia.dominio.dto.PessoaDTO;
import com.fiap.challenge.monitorenergia.dominio.entities.Endereco;
import com.fiap.challenge.monitorenergia.dominio.entities.Pessoa;
import com.fiap.challenge.monitorenergia.dominio.entities.Usuario;
import com.fiap.challenge.monitorenergia.dominio.repositorio.IPessoaRepository;
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
public class PessoaService {
    @Autowired
    private IPessoaRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    public Page<PessoaDTO> findAll(PageRequest pageRequest) {
        Page<Pessoa> list = repository.findAll(pageRequest);
        return list.map(this::mapperEntityToDto);
    }

    public PessoaDTO findById(Long id) {
        Optional<Pessoa> entity = repository.findById(id);
        Pessoa pessoa = entity.orElseThrow(() -> new ControllerNotFoundException("Pessoa não encontrada"));
        return mapperEntityToDto(pessoa);
    }

    public PessoaDTO insert(PessoaDTO dto) {
        try {
            Pessoa pessoa = new Pessoa();

            Usuario usuario = usuarioService.findByName(dto.getUsuario());

            mapperDtoToEntity(dto, pessoa, usuario);
            pessoa = repository.save(pessoa);

            return  mapperEntityToDto(pessoa);
        }
        catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Pessoa não encontrada");
        }
    }

    public PessoaDTO update(Long id, PessoaDTO dto) {
        try {
            Pessoa pessoa = repository.getReferenceById(id);

            mapperDtoToEntity(dto, pessoa);

            pessoa = repository.save(pessoa);

            return mapperEntityToDto(pessoa);
        }
        catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Pessoa não encontrada");
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ControllerNotFoundException("Pessoa não encontrado");
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade dos dados");
        }
    }

    private void mapperDtoToEntity(PessoaDTO dto, Pessoa pessoa, Usuario usuario){
        pessoa.setNome(dto.getNome());
        pessoa.setDataNascimento(dto.getDataNascimento());
        pessoa.setSexo(dto.getSexo());
        pessoa.setParentesco(dto.getParentesco());
        pessoa.setUsuario(usuario);
    }

    private void mapperDtoToEntity(PessoaDTO dto, Pessoa pessoa){
        pessoa.setNome(dto.getNome());
        pessoa.setDataNascimento(dto.getDataNascimento());
        pessoa.setSexo(dto.getSexo());
        pessoa.setParentesco(dto.getParentesco());
    }

    private PessoaDTO mapperEntityToDto(Pessoa pessoa){
        PessoaDTO dto = new PessoaDTO();
        dto.setId(pessoa.getId());
        dto.setNome(pessoa.getNome());
        dto.setDataNascimento(pessoa.getDataNascimento());
        dto.setSexo(pessoa.getSexo());
        dto.setParentesco(pessoa.getParentesco());
        dto.setUsuario(pessoa.getUsuario().getNome());

        return dto;
    }

}
