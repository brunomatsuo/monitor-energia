package com.fiap.challenge.monitorenergia.controller;

import com.fiap.challenge.monitorenergia.controller.dto.EnderecoDTO;
import com.fiap.challenge.monitorenergia.repositorio.RepositorioEndereco;
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
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private RepositorioEndereco repositorioEndereco;

    @Autowired
    private Validator validator;

    @GetMapping
    public ResponseEntity getEnderecos(){
        Set<EnderecoDTO> enderecos = new HashSet<>();
        repositorioEndereco.getEnderecos().forEach(endereco -> enderecos.add(endereco.toEnderecoDTO()));
        return ResponseEntity.ok(enderecos);
    }

    @PostMapping
    public ResponseEntity cadastrarEndereco(@RequestBody EnderecoDTO enderecoDTO){
        Map<Path, String> violacoesToMap = validar(enderecoDTO);

        if(!violacoesToMap.isEmpty()){
            return ResponseEntity.badRequest().body(violacoesToMap);
        }

        repositorioEndereco.salvar(enderecoDTO.toEndereco());
        return ResponseEntity.created(null).body(enderecoDTO);
    }

    @DeleteMapping
    public ResponseEntity removerEndereco(@RequestBody EnderecoDTO enderecoDTO){
        boolean removido = repositorioEndereco.removerEndereco(enderecoDTO.toEndereco());
        if(!removido){
            return ResponseEntity.badRequest().body("Endereço não cadastrado.");
        }
        return ResponseEntity.ok().body("Endereço removido com sucesso.");
    }

    private <T> Map<Path, String> validar(T dto){
        Set<ConstraintViolation<T>> violacoes = validator.validate(dto);
        Map<Path, String> violacoesToMap = violacoes.stream().collect(Collectors.toMap(
                violacao -> violacao.getPropertyPath(), violacao -> violacao.getMessage()
        ));
        return violacoesToMap;
    }
}
