package com.fiap.challenge.monitorenergia.controller;

import com.fiap.challenge.monitorenergia.controller.dto.PessoaDTO;
import com.fiap.challenge.monitorenergia.dominio.Pessoa;
import com.fiap.challenge.monitorenergia.repositorio.RepositorioPessoa;
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
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private RepositorioPessoa repositorioPessoa;

    @Autowired
    private Validator validator;

    @GetMapping
    public ResponseEntity getPessoas() {
        Set<PessoaDTO> pessoasDto = new HashSet<>();
        repositorioPessoa.getPessoas().forEach(pessoa -> pessoasDto.add(pessoa.toPessoaDto()));
        return ResponseEntity.ok(pessoasDto);
    }

    @PostMapping
    public ResponseEntity criarPessoa(@RequestBody PessoaDTO pessoaDTO) {
        Map<Path, String> violacoesToMap = validar(pessoaDTO);

        if(!violacoesToMap.isEmpty()){
            return ResponseEntity.badRequest().body(violacoesToMap);
        }

        repositorioPessoa.salvar(pessoaDTO.toPessoa());
        return ResponseEntity.created(null).body(pessoaDTO);
    }

    @DeleteMapping
    public ResponseEntity removerPessoa(@RequestBody PessoaDTO pessoaDTO){
        boolean removido = repositorioPessoa.removerPessoa(pessoaDTO.toPessoa());
        if(!removido){
            return ResponseEntity.badRequest().body("Pessoa não existe.");
        }
        return ResponseEntity.ok("Pessoa removida com sucesso.");
    }

    private <T> Map<Path, String> validar(T dto){
        Set<ConstraintViolation<T>> violacoes = validator.validate(dto);
        Map<Path, String> violacoesToMap = violacoes.stream().collect(Collectors.toMap(
                violacao -> violacao.getPropertyPath(), violacao -> violacao.getMessage()
        ));
        return violacoesToMap;
    }

}