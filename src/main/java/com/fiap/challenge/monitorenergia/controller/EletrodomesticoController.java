package com.fiap.challenge.monitorenergia.controller;

import com.fiap.challenge.monitorenergia.controller.dto.EletrodomesticoDTO;
import com.fiap.challenge.monitorenergia.dominio.Eletrodomestico;
import com.fiap.challenge.monitorenergia.repositorio.RepositorioEletrodomestico;
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
@RequestMapping("/eletrodomesticos")
public class EletrodomesticoController {

    @Autowired
    private RepositorioEletrodomestico repositorioEletrodomestico;

    @Autowired
    private Validator validator;

    @GetMapping
    public ResponseEntity getEletrodomesticos(){
        Set<EletrodomesticoDTO> eletrodomesticos = new HashSet<>();
        repositorioEletrodomestico.getEletrodomesticos()
                .forEach(eletrodomestico -> eletrodomesticos.add(eletrodomestico.toEletrodomesticoDTO()));
        return ResponseEntity.ok(eletrodomesticos);
    }

    @PostMapping
    public ResponseEntity novoEletrodomestico(@RequestBody EletrodomesticoDTO eletrodomesticoDTO){
        Map<Path, String> violacoesToMap = validar(eletrodomesticoDTO);

        if(!violacoesToMap.isEmpty()){
            return ResponseEntity.badRequest().body(violacoesToMap);
        }

        repositorioEletrodomestico.salvar(eletrodomesticoDTO.toEletrodomestico());
        return ResponseEntity.created(null).body(eletrodomesticoDTO);
    }

    @DeleteMapping
    public ResponseEntity removerEletrodomestico(@RequestBody EletrodomesticoDTO eletrodomesticoDTO){
        boolean removido = repositorioEletrodomestico.removerEletrodomestico(eletrodomesticoDTO.toEletrodomestico());
        if(!removido){
            return ResponseEntity.badRequest().body("Eletrodoméstico não existe");
        }
        return ResponseEntity.ok("Eletroméstico removido com sucesso.");
    }

    private <T> Map<Path, String> validar(T dto){
        Set<ConstraintViolation<T>> violacoes = validator.validate(dto);
        Map<Path, String> violacoesToMap = violacoes.stream().collect(Collectors.toMap(
                violacao -> violacao.getPropertyPath(), violacao -> violacao.getMessage()
        ));
        return violacoesToMap;
    }
}
