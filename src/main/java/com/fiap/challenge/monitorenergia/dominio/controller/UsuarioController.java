package com.fiap.challenge.monitorenergia.dominio.controller;

import com.fiap.challenge.monitorenergia.dominio.dto.UsuarioDTO;
import com.fiap.challenge.monitorenergia.dominio.services.UsuarioService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private Validator validator;

    @GetMapping
    public ResponseEntity<Page<UsuarioDTO>> getUsuarios(
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "quatidade", defaultValue = "10") Integer quantidade,
            @RequestParam(value = "direcao", defaultValue = "DESC") String direcao,
            @RequestParam(value = "ordenacao", defaultValue = "nome") String ordenacao
    ){
        PageRequest pageRequest = PageRequest.of(pagina, quantidade, Sort.Direction.valueOf(direcao), ordenacao);
        var list = service.findAll(pageRequest);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> findById(
            @PathVariable Long id
    ){
        UsuarioDTO usuarioDTO = service.findById(id);
        return ResponseEntity.ok(usuarioDTO);
    }

    @PostMapping
    public ResponseEntity novoUsuario(@RequestBody UsuarioDTO dto){
        Map<Path, String> violacoesToMap = validar(dto);

        if(!violacoesToMap.isEmpty()){
            return ResponseEntity.badRequest().body(violacoesToMap);
        }

        UsuarioDTO usuarioDTO = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand((usuarioDTO.getId())).toUri();

        return ResponseEntity.created(uri).body(usuarioDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUsuario(
            @PathVariable Long id,
            @RequestBody UsuarioDTO dto
    ){
        Map<Path, String> violacoesToMap = validar(dto);

        if(!violacoesToMap.isEmpty()){
            return ResponseEntity.badRequest().body(violacoesToMap);
        }

        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerUsuario(
            @PathVariable Long id
    ){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private <T> Map<Path, String> validar(T dto){
        Set<ConstraintViolation<T>> violacoes = validator.validate(dto);
        Map<Path, String> violacoesToMap = violacoes.stream().collect(Collectors.toMap(
                violacao -> violacao.getPropertyPath(), violacao -> violacao.getMessage()
        ));
        return violacoesToMap;
    }

}
