package com.fiap.challenge.monitorenergia.dominio.services;

import com.fiap.challenge.monitorenergia.dominio.dto.UsuarioDTO;
import com.fiap.challenge.monitorenergia.dominio.entities.Usuario;
import com.fiap.challenge.monitorenergia.dominio.repositorio.IUsuarioRepository;
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
public class UsuarioService {

    @Autowired
    private IUsuarioRepository repository;

    public Page<UsuarioDTO> findAll(PageRequest pageRequest) {
        Page<Usuario> list = repository.findAll(pageRequest);
        return list.map(this::mapperEntityToDto);
    }

    public UsuarioDTO findById(Long id) {
        Optional<Usuario> entity = repository.findById(id);
        Usuario usuario = entity.orElseThrow(() -> new ControllerNotFoundException("Usuário não encontrado"));
        return mapperEntityToDto(usuario);
    }

    public UsuarioDTO insert(UsuarioDTO dto) {
        try {
            Usuario usuario = new Usuario();
            mapperDtoToEntity(dto, usuario);
            usuario = repository.save(usuario);

            return  mapperEntityToDto(usuario);
        }
        catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Usuário não encontrado");
        }
    }

    public UsuarioDTO update(Long id, UsuarioDTO dto) {
        try {
            Usuario usuario = repository.getReferenceById(id);

            mapperDtoToEntity(dto, usuario);

            usuario = repository.save(usuario);

            return mapperEntityToDto(usuario);
        }
        catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Usuário não encontrado");
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ControllerNotFoundException("Usuário não encontrado");
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade dos dados");
        }
    }

    private void mapperDtoToEntity(UsuarioDTO dto, Usuario usuario){
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setDataNascimento(dto.getDataNascimento());
        usuario.setSexo(dto.getSexo());
    }

    private UsuarioDTO mapperEntityToDto(Usuario usuario){
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setDataNascimento(usuario.getDataNascimento());
        dto.setSexo(usuario.getSexo());

        return dto;
    }

}
