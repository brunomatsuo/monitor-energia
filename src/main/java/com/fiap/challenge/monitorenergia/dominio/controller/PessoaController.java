package com.fiap.challenge.monitorenergia.dominio.controller;

import com.fiap.challenge.monitorenergia.dominio.dto.PessoaDTO;
import com.fiap.challenge.monitorenergia.dominio.services.PessoaService;
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
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService service;

    @Autowired
    private Validator validator;

    @GetMapping
    public ResponseEntity<Page<PessoaDTO>> getPessoas(
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
    public ResponseEntity<PessoaDTO> findById(
            @PathVariable Long id
    ){
        PessoaDTO pessoaDTO = service.findById(id);
        return ResponseEntity.ok(pessoaDTO);
    }

    @PostMapping
    public ResponseEntity novaPessoa(@RequestBody PessoaDTO dto){
        Map<Path, String> violacoesToMap = validar(dto);

        if(!violacoesToMap.isEmpty()){
            return ResponseEntity.badRequest().body(violacoesToMap);
        }

        PessoaDTO pessoaDTO = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand((pessoaDTO.getId())).toUri();

        return ResponseEntity.created(uri).body(pessoaDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity updatePessoa(
            @PathVariable Long id,
            @RequestBody PessoaDTO dto
    ){
        Map<Path, String> violacoesToMap = validar(dto);

        if(!violacoesToMap.isEmpty()){
            return ResponseEntity.badRequest().body(violacoesToMap);
        }

        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPessoa(
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