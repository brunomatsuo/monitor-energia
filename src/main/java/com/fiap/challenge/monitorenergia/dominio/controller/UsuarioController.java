package com.fiap.challenge.monitorenergia.dominio.controller;

import com.fiap.challenge.monitorenergia.dominio.dto.UsuarioDTO;
import com.fiap.challenge.monitorenergia.dominio.repositorio.RepositorioUsuario;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Autowired
    private Validator validator;

    @GetMapping
    public ResponseEntity getUsuarios() {
        Set<UsuarioDTO> usuariosDto = new HashSet<>();
        repositorioUsuario.getUsuarios().forEach(usuario -> usuariosDto.add(usuario.toUsuarioDto()));
        return ResponseEntity.ok(usuariosDto);
    }

    @PostMapping
    public ResponseEntity criarUsuario(@RequestBody UsuarioDTO usuarioDto) {
        Map<Path, String> violacoesToMap = validar(usuarioDto);

        if(!violacoesToMap.isEmpty()){
            return ResponseEntity.badRequest().body(violacoesToMap);
        }

        repositorioUsuario.salvar(usuarioDto.toUsuario());
        return ResponseEntity.created(null).body(usuarioDto);
    }

    @DeleteMapping
    public ResponseEntity removerUsuario(@RequestBody UsuarioDTO usuarioDTO){
        boolean removido = repositorioUsuario.removerUsuario(usuarioDTO.toUsuario());
        if(!removido){
            return ResponseEntity.badRequest().body("Usuário não existe.");
        }
        return ResponseEntity.ok("Usuário removido com sucesso.");
    }

    private <T> Map<Path, String> validar(T dto){
        Set<ConstraintViolation<T>> violacoes = validator.validate(dto);
        Map<Path, String> violacoesToMap = violacoes.stream().collect(Collectors.toMap(
                violacao -> violacao.getPropertyPath(), violacao -> violacao.getMessage()
        ));
        return violacoesToMap;
    }

}
